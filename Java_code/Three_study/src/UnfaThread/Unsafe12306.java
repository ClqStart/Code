package UnfaThread;

/**
 * 1.线程安全
 * @author clq
 * synchronized
 * 2同步方法
 * 2同步块
 */
public class Unsafe12306 {
	public static void main(String[] args) {
		//一份资源
		safeWeb12306 web =new safeWeb12306();
		System.out.println(Thread.currentThread().getName());
		//多个代理
		new Thread(web,"码畜").start();
		new Thread(web,"码农").start();
		new Thread(web,"码蟥").start();;
	}
	
}

class safeWeb12306 implements Runnable{
	//票数
	private int ticketNums = 4;
	private boolean flag =true;
	
	@Override
	public void run() {
		while(flag) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			test();
		}
	}
	
	//线程安全 同步  锁对象This(safeWeb12306 )资源
	public synchronized void test() {
		if(ticketNums<=0) {
			flag =false;
			return;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
	}
}
	

