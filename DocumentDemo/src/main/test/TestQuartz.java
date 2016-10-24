import com.yangyang.quartz.HelloJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-hibernate.xml")
public class TestQuartz {


    @Test
    public void testquartz1() throws SchedulerException, InterruptedException {
        Scheduler schedualr = StdSchedulerFactory.getDefaultScheduler();
        schedualr.start();


        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())
                .build();

        schedualr.scheduleJob(job,trigger);

        Thread.sleep(10000);
        schedualr.shutdown();
    }

    @Test
    public void testSchedual2() throws SchedulerException {
        Scheduler schedual = StdSchedulerFactory.getDefaultScheduler();
        schedual.start();

        JobDetail jobDrtail = new JobDetailImpl("jobname",Scheduler.DEFAULT_GROUP,HelloJob.class);
        jobDrtail.getJobDataMap().put("message","helloworld");
        jobDrtail.getJobDataMap().put("counter","10");


    }
}
