package Threed_Terminate;

/**
 * 礼让线程不一定成功
 * @author clq
 *
 */
public class YieldDemon {
	public static void main(String[] args) {
		
	MyYield my = new MyYield();
	new Thread(my,"a").start();
	new Thread(my,"b").start();

}

}
class MyYield implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"---->start");
		Thread.yield();
		System.out.println(Thread.currentThread().getName()+"---->end");
	}
	
}