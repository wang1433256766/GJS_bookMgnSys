package cn.com.miaoto.timerjob.job;

import cn.com.miaoto.common.UserInfoSynchronize;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * 用户信息同步job
 * Created by hx on 2017/9/11.
 */
public class SynUserInfoJob implements Job {

    public static final Logger LOGGER = LoggerFactory.getLogger(SynUserInfoJob.class);

    @Autowired
    private UserInfoSynchronize userInfoSynchronize;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        LOGGER.debug("SynUserInfoJob excute start");
        userInfoSynchronize.excute();
        LOGGER.debug("SynUserInfoJob excute end");
    }

}
