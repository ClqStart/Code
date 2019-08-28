
 
package com.sxt.cooperation;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class HelloJob implements Job {

    public HelloJob() {
    }

    public void execute(JobExecutionContext context)
        throws JobExecutionException {
    	
    	System.out.println("-----------------start--------------");
    	System.out.println("HELLO WORLD ! "+ new Date());
    	System.out.println("-----------------end--------------");

     
    }

}
