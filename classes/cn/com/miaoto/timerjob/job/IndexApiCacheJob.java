package cn.com.miaoto.timerjob.job;

import cn.com.miaoto.common.BookStats;
import cn.com.miaoto.modules.book.inf.GetHotBookService;
import cn.com.miaoto.modules.book.inf.GetHotBorrowService;
import cn.com.miaoto.modules.book.inf.GetNewBookService;
import cn.com.miaoto.modules.book.model.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * 更新内存里保存的新书推荐, 热门借阅, 热门图书的list
 * Created by hx on 2017/9/6.
 */
@Component
public class IndexApiCacheJob implements Job {

    public static final Logger LOGGER = LoggerFactory.getLogger(IndexApiCacheJob.class);

    @Autowired
    private BookStats bookStats;

    @Autowired
    private GetHotBookService getHotBookService;

    @Autowired
    private GetHotBorrowService getHotBorrowService;

    @Autowired
    private GetNewBookService getNewBookService;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        try {
            // 热门图书
            GetHotBookReq reqBean = new GetHotBookReq();
            GetHotBookResp respBean = new GetHotBookResp();
            respBean = getHotBookService.getHotBook(reqBean, respBean);
            if (respBean != null) {
                if (respBean.getResultCode() == 0 || respBean.getBookList() == null || respBean.getBookList().size() == 0) {
                    LOGGER.error("get hot book failed");
                } else {
                    bookStats.setHotBooks(respBean.getBookList());
                }
            }


            // 热门借阅
            GetHotBorrowReq reqBean2 = new GetHotBorrowReq();
            GetHotBorrowResp respBean2 = new GetHotBorrowResp();
            respBean2 = getHotBorrowService.getHotBorrow(reqBean2, respBean2);
            if (respBean2 != null) {
                if (respBean2.getResultCode() == 0 || respBean2.getBookList() == null || respBean2.getBookList().size() == 0) {
                    LOGGER.error("get hot borrow failed");
                } else {
                    bookStats.setHotBorrow(respBean2.getBookList());
                }
            }


            // 新书推荐
            GetNewBookReq reqBean3 = new GetNewBookReq();
            GetNewBookResp respBean3 = new GetNewBookResp();
            respBean3 = getNewBookService.getNewBook(reqBean3, respBean3);
            if (respBean3 != null) {
                if (respBean3.getResultCode() == 0 || respBean3.getBookEntityList() == null || respBean3.getBookEntityList().size() == 0) {
                    LOGGER.error("get new book failed");
                } else {
                    bookStats.setNewBooks(respBean3.getBookEntityList());
                }
            }


        } catch (Exception e) {
            LOGGER.error("IndexApiCacheJob catch exception :", e);
        }
    }
}
