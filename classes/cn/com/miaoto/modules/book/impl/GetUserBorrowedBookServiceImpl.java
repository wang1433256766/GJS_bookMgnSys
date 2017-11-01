package cn.com.miaoto.modules.book.impl;

import cn.com.miaoto.common.mvcBean.AbstractService;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.modules.book.inf.GetUserBorrowedBookService;
import cn.com.miaoto.modules.book.model.GetUserBorrowedBookReq;
import cn.com.miaoto.modules.book.model.GetUserBorrowedBookResp;
import cn.com.miaoto.pojo.Book;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.pojo.Borrow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hx on 2017/7/22.
 */
@Service
public class GetUserBorrowedBookServiceImpl extends AbstractService<GetUserBorrowedBookReq, GetUserBorrowedBookResp> implements GetUserBorrowedBookService {

    @Resource
    BorrowDao borrowDao;

    @Resource
    BookEntityDao bookEntityDao;

    @Resource
    BookDao bookDao;

    @Override
    public GetUserBorrowedBookResp getBookInfoByUid(GetUserBorrowedBookReq reqBean, GetUserBorrowedBookResp respBean) {
        return (GetUserBorrowedBookResp) super.execute(reqBean, respBean);
    }

    @Override
    public boolean checkInput(GetUserBorrowedBookReq reqBean) {
        return true;
    }

    @Override
    protected void handle(GetUserBorrowedBookReq reqBean, GetUserBorrowedBookResp respBean) throws Exception {
        List<Borrow> borrowList = borrowDao.selectBorrowedByUser(reqBean.getUid());

        //查询已还图书信息
        if (reqBean.getBackBookBeid() != 0) {
            BookEntity queryBookEntity = new BookEntity();
            queryBookEntity.setBarcode(reqBean.getBackBookBeid());
            List<BookEntity> bookEntityTmp = bookEntityDao.queryBookEntityByBar(queryBookEntity);
            Book book = bookDao.queryBookById(bookEntityTmp.get(0).getBid());
            respBean.setBackBook(book);
        }

        respBean.setBorrows(borrowList);
    }

}
