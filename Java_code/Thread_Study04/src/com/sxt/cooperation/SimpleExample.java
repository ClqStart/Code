 
package com.sxt.cooperation;

import static org.quartz.DateBuilder.evenSecondDateAfterNow;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SimpleExample {

  public void run() throws Exception {
    

    
	 //1.创建Scheduler的工厂
    SchedulerFactory sf = new StdSchedulerFactory();
    //2.获取工厂调度器
    Scheduler sched = sf.getScheduler();

    //3.创建JobDetail
    JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

   //时间
    Date runTime = evenSecondDateAfterNow();
    //4触发器
//    Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
    	Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime)
    			.withSchedule(simpleSchedule().withIntervalInSeconds(5).withRepeatCount(4)).build();
   //5.注册
    sched.scheduleJob(job, trigger);
  
    
    //6.启动
    sched.start();

 
    try {
      // wait 65 seconds to show job
      Thread.sleep(50L * 1000L);
      // executing...
    } catch (Exception e) {
      
    }

   
    sched.shutdown(true);
  
  }

  public static void main(String[] args) throws Exception {

    SimpleExample example = new SimpleExample();
    example.run();

  }

}
