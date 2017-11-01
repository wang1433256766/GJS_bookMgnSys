package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.modules.book.inf.GetHotBorrowService;
import cn.com.miaoto.modules.book.model.GetHotBorrowReq;
import cn.com.miaoto.modules.book.model.GetHotBorrowResp;
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
public class GetHotBorrowServiceImpl extends AbstractService<GetHotBorrowReq, GetHotBorrowResp> implements GetHotBorrowService {

    public static final Logger LOGGER = LoggerFactory.getLogger(GetHotBorrowServiceImpl.class);

    @Resource
    BookDao bookDao;

    @Override
    public GetHotBorrowResp getHotBorrow(GetHotBorrowReq reqBean, GetHotBorrowResp respBean) {
        return (GetHotBorrowResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetHotBorrowReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetHotBorrowReq reqBean, GetHotBorrowResp respBean) throws Exception {
        List<Book> bookList = bookDao.queryHotBorrow();
        if(bookList == null) {
            LOGGER.error("query hot borrow failed");
            return;
        }
        if(bookList.size() == 0) {
            LOGGER.warn("get hot borrow size is 0");
            return;
        }
        respBean.setResultCode(1);
        respBean.setBookList(bookList);
    }

}
