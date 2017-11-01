package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.FuzzySearchService;
import cn.com.miaoto.modules.book.model.FuzzySearchReq;
import cn.com.miaoto.modules.book.model.FuzzySearchResp;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/31.
 */
@Service
public class FuzzySearchServiceImpl extends AbstractService<FuzzySearchReq, FuzzySearchResp> implements FuzzySearchService {
    public static final Logger LOGGER = LoggerFactory.getLogger(FuzzySearchServiceImpl.class);

    @Resource
    BookDao bookDao;

    @Resource
    BookEntityDao bookEntityDao;

    @Override
    public FuzzySearchResp fuzzySearch(FuzzySearchReq reqBean, FuzzySearchResp respBean) {
        return (FuzzySearchResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(FuzzySearchReq reqBean) {
        return true;
    }

    @Override
    protected void handle(FuzzySearchReq reqBean, FuzzySearchResp respBean) throws Exception {
        String bookName = reqBean.getBookName();
        List<String> keyList = StringUtil.splitKey(bookName);
        respBean.setKeyList(keyList);

        // 拼接cotains
        String sql = "(b_name, b_foreign_name), '";
        for (String zd : keyList) {
            sql = sql + "\"" + zd + "\" and";
        }
        sql = sql.substring(0, sql.length() - 3);
        sql = sql + "'";
        reqBean.setBookNameSearch(sql);
        // 查询book
        List<Book> bookList = bookDao.fuzzySearchBook(reqBean);
        if (bookList == null) {
            LOGGER.error("fuzzy search failed");
            return;
        }
        // 查询总记录数
        int count = bookDao.fuzzyCount(reqBean);
        //查询实体
        for (Book book : bookList) {
            // 总数量
            int allCount = bookEntityDao.queryBookEntityCount(book.getBid(), null);
            if (allCount != 0) {
                book.setCount(allCount);
            }
            //可借数量
            int available = bookEntityDao.queryBookEntityCount(book.getBid(), SiomConstants.BOOKSTATUS_AVAILABLE);
            if (available != 0) {
                book.setAvailable(available);
            }
        }

        respBean.setBookList(bookList);
        respBean.setAllRows(count);
        respBean.setBookList(bookList);
        respBean.setResultCode(1);

    }
}
