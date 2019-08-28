package UnfaThread;

public class Unsafe {
	public static void main(String[] args) {
		//一份资源
		UnsafeWeb12306 web =new UnsafeWeb12306();
		System.out.println(Thread.currentThread().getName());
		//多个代理
		new Thread(web,"码畜").start();
		new Thread(web,"码农").start();
		new Thread(web,"码蟥").start();;
	}
	
}

class UnsafeWeb12306 implements Runnable{
	//票数
	private int ticketNums = 20;
	private boolean flag =true;
	
	@Override
	public void run() {
		while(flag) {
			test();
		}
	}
	
	public void test() {
		if(ticketNums<0) {
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
	

