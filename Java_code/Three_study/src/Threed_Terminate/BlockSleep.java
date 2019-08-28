package Threed_Terminate;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BlockSleep {
	public static void  main(String[] args) throws InterruptedException{
		
		Date endTime = new Date(System.currentTimeMillis()+1000*10);
		long end = endTime.getTime();
		
	while(true) {
		System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
		//静态
		Thread.sleep(1000);
		endTime = new Date(endTime.getTime()-1000);
		if(end-10000>endTime.getTime()) {
			break;
		}
			
	}
	}
	
	
	
	 
}
