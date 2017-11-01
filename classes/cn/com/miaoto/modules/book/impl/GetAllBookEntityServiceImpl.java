package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.GetAllBookEntityService;
import cn.com.miaoto.modules.book.model.GetAllBookEntityReq;
import cn.com.miaoto.modules.book.model.GetAllBookEntityResp;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2017/7/27.
 */
@Service
public class GetAllBookEntityServiceImpl extends AbstractService<GetAllBookEntityReq, GetAllBookEntityResp> implements GetAllBookEntityService {
    public static final Logger LOGGER = LoggerFactory.getLogger(GetAllBookEntityServiceImpl.class);

    @Resource
    BookDao bookDao;

    @Resource
    BookEntityDao bookEntityDao;

    @Override
    public GetAllBookEntityResp getAllBookEntity(GetAllBookEntityReq reqBean, GetAllBookEntityResp respBean) {
        return (GetAllBookEntityResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetAllBookEntityReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetAllBookEntityReq reqBean, GetAllBookEntityResp respBean) throws Exception {
        int page = reqBean.getSearchFilter().getPage();
        int rows = reqBean.getSearchFilter().getRows();
        if (reqBean.getSearchFilter().getPage() < 1) {
            return;
        }
        reqBean.getSearchFilter().setBegin((page - 1) * rows);
        reqBean.getSearchFilter().setEnd((page) * rows);

        if (!StringUtil.isEmpty(reqBean.getBookEntity().getStatusStr())) {
            String[] strArr = reqBean.getBookEntity().getStatusStr().split(",");
            ArrayList<Integer> list = new ArrayList<>();
            for (String str : strArr) {
                list.add(Integer.parseInt(str));
            }
            reqBean.getBookEntity().setStatusList(list);
        }

        List<BookEntity> bookEntityList = bookEntityDao.selectall(reqBean);
        if (bookEntityList == null) {
            return;
        }
//        for(int i = 0; i < bookEntityList.size(); i++){
//            Book book = bookDao.queryBookById(bookEntityList.get(i).getBid());
//            bookEntityList.get(i).setBook(book);
//        }

        //get bookentity count
        int count = bookEntityDao.querySearchCount(reqBean);

        respBean.setBookEntityList(bookEntityList);
        respBean.setPage(page);
        respBean.setCount(count);
    }
}
