package cn.com.miaoto.timerjob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by hx on 2017/8/15.
 */
@Component
public class QuartzManager {

    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "SIOM_JOBGROUP_NAME";
    private static String TRIGGER_NAME = "SIOM_TRIGGER_NAME";

    @SuppressWarnings("unchecked")
    public static void addJob(String jobName, Class cls, String time, String triggerName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();

            //创建job
            JobDetail jobDetail= JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME).build();// 任务名，任务组，任务执行类, cls

            // 触发器builder
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withIdentity(TRIGGER_NAME, triggerName);
            triggerBuilder.startNow();
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(time));

            //触发器
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();

            //
            sched.scheduleJob(jobDetail, trigger);
            // 启动
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static void modifyJobTime(String jobName, String time, String triggerName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, triggerName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                JobKey jobKey =JobKey.jobKey(jobName,JOB_GROUP_NAME);
                JobDetail jobDetail = sched.getJobDetail(jobKey);
                Class objJobClass = jobDetail.getJobClass();
                removeJob(jobName, triggerName);
                addJob(jobName, objJobClass, time, triggerName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeJob(String jobName, String triggerName) {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            JobKey jobKey =JobKey.jobKey(jobName,JOB_GROUP_NAME);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,triggerName);
            sched.pauseTrigger(triggerKey);// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(jobKey);// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void startJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void shutdownJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
