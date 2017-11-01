package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.modules.book.inf.GetNewBookService;
import cn.com.miaoto.modules.book.model.GetNewBookReq;
import cn.com.miaoto.modules.book.model.GetNewBookResp;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/22.
 */
@Service
public class GetNewBookServiceImpl extends AbstractService<GetNewBookReq, GetNewBookResp> implements GetNewBookService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetNewBookServiceImpl.class);

    @Resource
    BookEntityDao bookEntityDao;

    @Override
    public GetNewBookResp getNewBook(GetNewBookReq reqBean, GetNewBookResp respBean) {
        return (GetNewBookResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetNewBookReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetNewBookReq reqBean, GetNewBookResp respBean) throws Exception {
        List<BookEntity> bookEntityList = bookEntityDao.selectNew();
        if(bookEntityList == null) {
            LOGGER.error("get new book failed");
            return;
        }
        if(bookEntityList.size() == 0) {
            LOGGER.error("get new book size is 0");
            return;
        }
        respBean.setResultCode(1);
        respBean.setBookEntityList(bookEntityList);
    }

}
