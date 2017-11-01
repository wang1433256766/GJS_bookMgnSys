package cn.com.miaoto.timerjob.job;

import cn.com.miaoto.common.BookStats;
import cn.com.miaoto.dao.inf.BookDao;
import cn.com.miaoto.pojo.Book;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hx on 2017/8/23.
 */
@Component
@Lazy(true)
public class BookBorrowStatsJob implements Job {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookBorrowStatsJob.class);

    @Autowired
    private BookStats bookStats;

    @Autowired
    private BookDao bookDao;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        Map<Integer, Integer> map = new HashMap<>();
        map.putAll(bookStats.getBookBorrow());
        LOGGER.debug("book borrow map size = {}", map.size());
        bookStats.clearBookBorrow();

        try {
            LOGGER.warn("update book borrow stats start");
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Book book = bookDao.queryBookById((int) entry.getKey());
                long bookBorrowNum = 0;
                if (book.getBorrow() != null) {
                    bookBorrowNum = book.getBorrow();
                }
                Book updateBook = new Book();
                updateBook.setBid((int) entry.getKey());
                updateBook.setBorrow(bookBorrowNum + (long) entry.getValue());
                LOGGER.debug("update book borrow, book id = {}, increase = {}, resultNum = {}", (int) entry.getKey(), (long) entry.getValue(), bookBorrowNum + (long) entry.getValue());
                int effected = bookDao.updateBookStats(updateBook);
                if (effected == 0) {
                    LOGGER.error("update book borrow stats failed");
                }
            }
            LOGGER.warn("book borrow stats finished");
        } catch (Exception e) {
            LOGGER.error("book borrow stats catch Exception : ", e);
        }

    }
}
