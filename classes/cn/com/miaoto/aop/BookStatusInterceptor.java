package cn.com.miaoto.aop;

import cn.com.miaoto.common.SiomConstants;
import cn.com.miaoto.common.SystemSetting;
import cn.com.miaoto.common.exception.DBException;
import cn.com.miaoto.dao.inf.BookEntityDao;
import cn.com.miaoto.dao.inf.FollowDao;
import cn.com.miaoto.dao.inf.NotifyDao;
import cn.com.miaoto.modules.common.impl.SendMailServiceImpl;
import cn.com.miaoto.pojo.BookEntity;
import cn.com.miaoto.pojo.Follow;
import cn.com.miaoto.pojo.Notify;
import cn.com.miaoto.util.TemplateUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2017/8/17.
 */
@Component
@Aspect
public class BookStatusInterceptor {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookStatusInterceptor.class);

    @Resource
    NotifyDao notifyDao;

    @Resource
    FollowDao followDao;

    @Resource
    BookEntityDao bookEntityDao;

    @Resource
    SendMailServiceImpl sendMailServiceImpl;

    @Pointcut("execution(* cn.com.miaoto.dao.impl.BookEntityDaoImpl.updateBookStatus(..))")
    private void updateBookStatus() {
    }

    @AfterReturning(pointcut = "updateBookStatus() && args(barCode, status)", returning = "rvt")
    public void doAfterThrow(long barCode, int status, Object rvt) {

        LOGGER.trace("update book status barcode = {}, status = {}, return = {}", barCode, status, rvt);

        List<BookEntity> bookEntityList = null;

        // 状态不是变为可借状态就不发送通知
        if (status != 0) {
            return;
        }

        // 查询bid
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBarcode(barCode);

        try {
            bookEntityList = bookEntityDao.queryBookEntity(bookEntity);
            if (bookEntity == null || bookEntityList.size() == 0) {
                LOGGER.error("query bookentity failed");
                return;
            }
        } catch (DBException e) {
            LOGGER.error("catch exception : ;barcode = {}", e, barCode);
            return;
        }

        // 查询关注表
        Follow queryFollow = new Follow();
        queryFollow.setFoBeid(bookEntityList.get(0).getBook().getBid());
        queryFollow.setFoStatus(SiomConstants.FOLLOW_STATUS_FOLLOWED);
        try {
            List<Follow> followList = followDao.selectReminder(queryFollow);
            if (followList == null) {
                LOGGER.error("followList is null");
                return;
            }
            if (followList.size() == 0) {
                LOGGER.info("followList size is 0");
                return;
            }
            for (Follow follow : followList) {
                //发送邮件
                ArrayList<String> paras = new ArrayList<>();
                paras.add(follow.getUname());
                paras.add(follow.getBookName());


                sendMailServiceImpl.SEND_SUBMIT_EMAIL(
                        follow.getEmail(),
                        StringEscapeUtils.unescapeHtml4(SystemSetting.getValue(SiomConstants.SETTING_FOLLOW_TITLE, String.class)),
                        StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeHtml4(TemplateUtil.replace(SystemSetting.getValue(SiomConstants.SETTING_FOLLOW_TEMPLATE, String.class), paras)))
                );

                //发送站内信
                Notify notify = new Notify();
                notify.setTitle(SystemSetting.getValue(SiomConstants.SETTING_RESERVE_FAIL_MSG_TITLE, String.class));
                ArrayList<String> paraList2 = new ArrayList<String>() {{
                    add(follow.getBookName());
                }};
                notify.setContent(StringEscapeUtils.unescapeHtml4(TemplateUtil.replace(SystemSetting.getValue(SiomConstants.SETTING_FOLLOW_MSG_TEMPLATE, String.class), paraList2)));
                notify.setType(0);
                notify.setFrom(1);
                notify.setTo(follow.getFoUID());
                int effected = notifyDao.insert(notify);
                if (effected == 0) {
                    LOGGER.error("insert follow msg failed");
                }

                // 更新follow状态
                Follow updateFollow = new Follow();
                updateFollow.setFoID(follow.getFoID());
                updateFollow.setFoStatus(SiomConstants.FOLLOW_STATUS_REMINDER);
                int effected2 = followDao.updateStatus(updateFollow);
                if (effected2 == 0) {
                    LOGGER.error("update follow status failed");
                }
            }
        } catch (Exception e) {
            LOGGER.error("BookStatusInterceptor catch Exception :", e);
        }
    }
}
