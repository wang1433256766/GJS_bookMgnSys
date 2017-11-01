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
public class BookViewStatsJob implements Job {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookViewStatsJob.class);

    @Autowired
    private BookStats bookStats;

    @Autowired
    private BookDao bookDao;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.putAll(bookStats.getBookView());
        LOGGER.debug("bookview map size = {}", map.size());
        bookStats.clearBookView();

        try {
            LOGGER.warn("start book view stats");
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Book book = bookDao.queryBookById((int) entry.getKey());
                long bookViewNum = 0;
                if (book.getView() != null) {
                    bookViewNum = book.getView();
                }
                Book updateBook = new Book();
                updateBook.setBid((int) entry.getKey());
                updateBook.setView(bookViewNum + (long) entry.getValue());
                LOGGER.debug("update book view, book id = {}, increase = {}, resultNum = {}", (int) entry.getKey(), (long) entry.getValue(), bookViewNum + (long) entry.getValue());
                int effected = bookDao.updateBookStats(updateBook);
                if (effected == 0) {
                    LOGGER.error("update book view stats failed");
                }
            }
            LOGGER.warn("book view stats finished");
        } catch (Exception e) {
            LOGGER.error("book view stats catch Exception : ", e);
        }

    }
}
