package cn.com.miaoto.timerjob.job;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.modules.common.impl.SendMailServiceImpl;
import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.pojo.Notify;
import cn.com.miaoto.util.TemplateUtil;
import cn.com.miaoto.util.TimeUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * 预约超期提醒job
 * Created by hx on 2017/8/16.
 */
@Component
public class ReserveFailJob implements Job {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReserveFailJob.class);

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private SendMailServiceImpl sendMailServiceImpl;

    @Autowired
    private NotifyDao notifyDao;

    @Autowired
    private BookEntityDao bookEntityDao;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        try {
            List<Borrow> borrowList = borrowDao.selectReserveFail(Integer.parseInt(SystemSetting.getValue(SiomConstants.SETTING_RESERVE_FAIL, String.class)));
            if (borrowList == null) {
                LOGGER.error("query reserve fail list is null");
                return;
            }
            if (borrowList.size() == 0) {
                LOGGER.warn("query reserve fail list size is 0");
                return;
            }
            for (Borrow borrow : borrowList) {
                // 处理
                Borrow borrow1 = new Borrow();
                borrow1.setBeid(borrow.getBeid());
                borrow1.setRenewal(-1);
                borrow1.setBacktime(TimeUtil.getCurrentTimeStr());
                int effected1 = borrowDao.updateBorrow(borrow1);
                if (effected1 == 0) {
                    LOGGER.error("update borrow info failed");
                    continue;
                }
                int effected2 = bookEntityDao.updateBookStatus(borrow.getBeid(), SiomConstants.BOOKSTATUS_AVAILABLE);
                if (effected2 == 0) {
                    LOGGER.error("update book status failed");
                    continue;
                }
                // 邮件
                ArrayList<String> paraList = new ArrayList<String>() {{
                    add(borrow.getUserName());
                    add(borrow.getBookName());
                    add(borrow.getBeid() + "");
                }};
                sendMailServiceImpl.SEND_SUBMIT_EMAIL(
                        borrow.getEmail(),
                        SystemSetting.getValue(SiomConstants.SETTING_RESERVE_FAIL_TITLE, String.class),
                        TemplateUtil.replace(SystemSetting.getValue(SiomConstants.SETTING_RESERVE_FAIL_TEMPLATE, String.class), paraList)
                );
                // 站内信
                Notify notify = new Notify();
                notify.setTitle(SystemSetting.getValue(SiomConstants.SETTING_RESERVE_FAIL_MSG_TITLE, String.class));
                ArrayList<String> paraList2 = new ArrayList<String>() {{
                    add(borrow.getBookName());
                    add(borrow.getBeid() + "");
                }};
                notify.setContent(TemplateUtil.replace(SystemSetting.getValue(SiomConstants.SETTING_RESERVE_FAIL_MSG_TEMPLATE, String.class), paraList2));
                notify.setType(0);
                notify.setFrom(1);
                notify.setTo(borrow.getUid());
                int effected = notifyDao.insert(notify);
                if (effected == 0) {
                    LOGGER.error("insert reserve fail  msg failed");
                }

            }
        } catch (Exception e) {
            LOGGER.error("ReserveFailJob.excute() catch exception : ", e);
        }

    }
}
