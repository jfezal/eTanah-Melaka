/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.scheduler;

/**
 *
 * @author mohd.faidzal
 */
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTrigger {

    public void trigger() throws SchedulerException {
//	JobKey jobKeyA = new JobKey("jobA", "group1");
//    	JobDetail jobA = JobBuilder.newJob(JobA.class)
//		.withIdentity(jobKeyA).build();
//
//    	JobKey jobKeyB = new JobKey("jobB", "group1");
//    	JobDetail jobB = JobBuilder.newJob(JobB.class)
//		.withIdentity(jobKeyB).build();

        JobKey jobKeyC = new JobKey("jobC", "group1");
        JobDetail jobC = JobBuilder.newJob(IspeksJob.class)
                .withIdentity(jobKeyC).build();

    	Trigger trigger1 = TriggerBuilder
		.newTrigger()
		.withIdentity("dummyTriggerName1", "group1")
		.withSchedule(
			CronScheduleBuilder.cronSchedule("0 */2 * ? * *"))
		.build();
//    	
//    	Trigger trigger2 = TriggerBuilder
//		.newTrigger()
//		.withIdentity("dummyTriggerName2", "group1")
//		.withSchedule(
//			CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
//		.build();
        Trigger trigger3 = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName3", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 15 17 * * ? *"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();
    	scheduler.scheduleJob(jobC, trigger1);
//    	scheduler.scheduleJob(jobB, trigger2);
//        scheduler.scheduleJob(jobC, trigger3);
    }

    public static void main(String[] args) throws Exception {
CronTrigger trig = new CronTrigger();
trig.trigger();
    }
}
