package cn.com.miaoto.timerjob.job;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.dao.inf.BorrowDao;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.modules.common.impl.SendMailServiceImpl;
import cn.com.miaoto.pojo.Borrow;
import cn.com.miaoto.pojo.Notify;
import cn.com.miaoto.util.TemplateUtil;
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
 * 催还图书job
 * Created by hx on 2017/8/15.
 */
@Component
public class ReminderBookJob implements Job {

    public static final Logger LOGGER = LoggerFactory.getLogger(ReminderBookJob.class);

    @Autowired
    BorrowDao borrowDao;

    @Autowired
    SendMailServiceImpl sendMailServiceImpl;

    @Autowired
    NotifyDao notifyDao;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        //催还图书
        try {
            List<Borrow> borrowList = borrowDao.selectReminder();
            if(borrowList == null || borrowList.size() == 0) {
                LOGGER.warn("ReminderBookJob query reminder is null");
                return;
            }
            for(Borrow borrow : borrowList) {
                //发送邮件
                ArrayList<String> paraList = new ArrayList<String>(){{
                    add(borrow.getUserName());
                    add(borrow.getBookName());
                    add(borrow.getBeid()+"");
                    add(borrow.getBacktime());
                }};
                sendMailServiceImpl.SEND_SUBMIT_EMAIL(
                        borrow.getEmail(),
                        SystemSetting.getValue(SiomConstants.SETTING_REMINDER_TITLE, String.class),
                        TemplateUtil.replace(SystemSetting.getValue(SiomConstants.SETTING_REMINDER_TEMPLATE, String.class), paraList)
                );
                // 站内信
                Notify notify  = new Notify();
                notify.setTitle(SiomConstants.SETTING_REMINDER_MSG_TITLE);
                ArrayList<String> paraList2 = new ArrayList<String>(){{
                    add(borrow.getBookName());
                    add(borrow.getBeid()+"");
                    add(borrow.getBacktime());
                }};
                notify.setContent(TemplateUtil.replace(SystemSetting.getValue(SiomConstants.SETTING_REMINDER_MSG_TEMPLATE, String.class), paraList2));
                notify.setType(0);
                notify.setFrom(1);
                notify.setTo(borrow.getUid());
                int effected = notifyDao.insert(notify);
                if(effected == 0) {
                    LOGGER.error("insert reminder msg failed");
                }
            }
        } catch (Exception e) {
            LOGGER.error("ReminderBookJob catch exception :", e);
        }
    }
}
