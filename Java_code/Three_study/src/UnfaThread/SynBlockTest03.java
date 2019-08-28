package UnfaThread;

/**
 * 1.线程安全
 * @author clq
 * synchronized
 * 2同步方法
 * 2同步块
 */
public class SynBlockTest03 {
	public static void main(String[] args) {
		//一份资源
		SynWeb12306 web =new SynWeb12306();
		System.out.println(Thread.currentThread().getName());
		//多个代理
		new Thread(web,"码畜").start();
		new Thread(web,"码农").start();
		new Thread(web,"码蟥").start();;
	}
	
}

class SynWeb12306 implements Runnable{
	//票数
	private int ticketNums = 10;
	private boolean flag =true;
	
	@Override
	public void run() {
		while(flag) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			test3();
		}
	}
	
	//线程安全 同步  锁对象This(safeWeb12306 )资源
	public void test1() {
		synchronized (this) {
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
	//锁的是不变的对象
	public void test2() {
		synchronized ((Integer) ticketNums) {
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
	public void test3() {
		//尽可能锁定合理位置（不看代码，☞数据的完整性）
		//double check
		if(ticketNums<=0) {
			flag =false;
			return;
		}
		//是否为最后一张票
		synchronized (this) { 
			if(ticketNums<=0) {
				flag =false;
				return;
			}
		}
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--);
		
	}
}


