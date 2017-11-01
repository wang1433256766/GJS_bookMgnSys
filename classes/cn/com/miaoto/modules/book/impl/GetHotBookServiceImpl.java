package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.modules.book.inf.GetHotBookService;
import cn.com.miaoto.modules.book.model.GetHotBookReq;
import cn.com.miaoto.modules.book.model.GetHotBookResp;
import cn.com.miaoto.pojo.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/8/22.
 */
@Service
public class GetHotBookServiceImpl extends AbstractService<GetHotBookReq, GetHotBookResp> implements GetHotBookService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetHotBookServiceImpl.class);

    @Resource
    BookDao bookDao;

    @Override
    public GetHotBookResp getHotBook(GetHotBookReq reqBean, GetHotBookResp respBean) {
        return (GetHotBookResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetHotBookReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetHotBookReq reqBean, GetHotBookResp respBean) throws Exception {
        List<Book> bookList = bookDao.queryHotBook();
        if(bookList == null) {
            LOGGER.error("query hot book failed");
            return;
        }
        respBean.setBookList(bookList);
        respBean.setResultCode(1);
    }

}
