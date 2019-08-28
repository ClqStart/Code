package Threed_Terminate;
/**
 * 1优先级设置
 * NORM_PRIORITY 5
 * MIN_PRIORITY 1
 * MAX_PRIORITY 10
 * 2表示一种概率
 * @author clq
 *
 */

public class PriorityTest {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getPriority());
		
		MyPriority tt= new MyPriority();
		
		Thread t1 = new Thread(tt);
		Thread t2= new Thread(tt);
		Thread t3 = new Thread(tt);
		Thread t4 = new Thread(tt);
		Thread t5 = new Thread(tt);
		Thread t6 = new Thread(tt);
		
		
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		t4.setPriority(Thread.MIN_PRIORITY);
		t5.setPriority(Thread.MIN_PRIORITY);
		t6.setPriority(Thread.MIN_PRIORITY);
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		
	}

}


class MyPriority  implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"----"+Thread.currentThread().getPriority());
		
	}
	
}