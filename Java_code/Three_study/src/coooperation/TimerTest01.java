package coooperation;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/*
 * 1.Timer和TimerTask
 * 
 */
public class TimerTest01 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		
		//timer.schedule(new MyTask(), 5000);  5秒后执行
		//timer.schedule(new MyTask(), 1000,200); //1秒后每200毫秒执行一次
		Calendar cal = new GregorianCalendar(2019,6,26,21,24,50);
		timer.schedule(new MyTask(), cal.getTime(),200);
	}
}

class MyTask extends TimerTask{

	@Override
	public void run() {
	for(int i =0;i<10;i++) {
		System.out.println("反恐大脑休息一会");	
	}
	System.out.println("ens------");	
	}
}