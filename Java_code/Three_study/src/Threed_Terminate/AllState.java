package Threed_Terminate;

import java.lang.Thread.State;

/**
 * 线程状态
 * @author clq
 *
 */

public class AllState {
	public static void main(String[] args) {
		
		Thread t = new Thread(()->{
			
			for(int i=0;i<5;i++) {
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("==========");
			}
			
		});
	
	State state = t.getState();
	System.out.println(state);
		
	t.start();
	state = t.getState();
	System.out.println(state);
	
	
	while(state != Thread.State.TERMINATED) {
		//活动的线程数
		int num = Thread.activeCount();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		state = t.getState();
		System.out.println(state);
	}
	
	state = t.getState();
	System.out.println(state);
	}

}
