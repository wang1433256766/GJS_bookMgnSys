package cn.com.miaoto.timerjob;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.timerjob.job.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by hx on 2017/8/15.
 */
@Component
public class InitAllJobs {

    public static final Logger LOGGER = LoggerFactory.getLogger(InitAllJobs.class);

    @PostConstruct
    public void init() {
        // 还书提醒 中午12点发送
        QuartzManager.addJob(SiomConstants.JOB_REMINDERBOOK, ReminderBookJob.class, "0 0 12 * * ?", SiomConstants.JOB_REMINDERBOOK_TRIGGER);
        //QuartzManager.addJob(SiomConstants.JOB_REMINDERBOOK, ReminderBookJob.class, "0 0/2 * * * ?");

        // 预约超期提醒  预约功能已删除
        //QuartzManager.addJob(SiomConstants.JOB_RESERVE_FAIL, ReserveFailJob.class, "0 0 1 * * ?", SiomConstants.JOB_RESERVE_FAIL_TRIGGER);
        //QuartzManager.addJob(SiomConstants.JOB_RESERVE_FAIL, ReserveFailJob.class, "0 0/2 * * * ?", SiomConstants.JOB_RESERVE_FAIL_TRIGGER);

        // 图书浏览统计
        QuartzManager.addJob(SiomConstants.JOB_BOOK_VIEW, BookViewStatsJob.class, "0 0 2 * * ?", SiomConstants.JOB_BOOK_VIEW_TRIGGER);
        //QuartzManager.addJob(SiomConstants.JOB_BOOK_VIEW, BookViewStatsJob.class, "0 0/2 * * * ?", SiomConstants.JOB_BOOK_VIEW_TRIGGER);

        // 图书借阅统计
        QuartzManager.addJob(SiomConstants.JOB_BOOK_BORROW, BookBorrowStatsJob.class, "0 0 2 * * ?", SiomConstants.JOB_BOOK_BORROW_TRIGGER);
        //QuartzManager.addJob(SiomConstants.JOB_BOOK_BORROW, BookBorrowStatsJob.class, "0 0/2 * * * ?", SiomConstants.JOB_BOOK_BORROW_TRIGGER);

        // index api cache
        QuartzManager.addJob(SiomConstants.JOB_INDEXAPICACHE, IndexApiCacheJob.class, "0 0 2 * * ?", SiomConstants.JOB_INDEXAPICACHE_TRIGGER);
        //QuartzManager.addJob(SiomConstants.JOB_INDEXAPICACHE, IndexApiCacheJob.class, "0 0/2 * * * ?", SiomConstants.JOB_INDEXAPICACHE_TRIGGER);

        // index api cache
        QuartzManager.addJob(SiomConstants.JOB_SYNUSERINFO, SynUserInfoJob.class, "0 0 2 * * ?", SiomConstants.JOB_SYNUSERINFO_TRIGGER);
        //QuartzManager.addJob(SiomConstants.JOB_SYNUSERINFO, SynUserInfoJob.class, "0 0/2 * * * ?", SiomConstants.JOB_SYNUSERINFO_TRIGGER);

    }
}
