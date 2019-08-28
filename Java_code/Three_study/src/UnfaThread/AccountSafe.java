package UnfaThread;

public class AccountSafe {
	public static void main(String[] args) {
		
	
	MyAccount a1 = new MyAccount("结婚彩礼",200);
	Thread t1 =  new Thread(new SafedrawMoney1(a1,80));
	
	Thread t2 =  new Thread(new SafedrawMoney1(a1,70));
	
	t1.setName("老婆");
	t2.setName("老子");
	t1.start(); // 你取钱
	t2.start(); // 你老婆取钱
	
	}	
	
}
//class 	MyAccount {
//	String name;
//	int money;
//	
//	public MyAccount(String name,int money) {
//		this.money = money;
//		this.name = name;
//		
//	}
//
//	}
	

class SafedrawMoney1  implements Runnable{
	
	MyAccount  Mc;
	int drawMoney;
	int drawTotall;
	
	

	public SafedrawMoney1(MyAccount MyAccount, int drawMoney) {
		super();
		this.Mc = MyAccount;
		this.drawMoney = drawMoney;
	}



	@Override
	public void run() {
		
			tesy();
		}
		
	//锁定目标不对,需要锁定对象,需要同步块
//	public synchronized  void tesy() {
	public  void tesy() {
		if(Mc.money<=0)	{
				return;
			}
		//同步块
		synchronized (Mc) {
			
		if(Mc.money -drawMoney<0) {
				return;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	
		Mc.money -=drawMoney;
		drawTotall +=drawMoney;
		System.out.println( Thread.currentThread()+ "--账户余额：" + Mc.money);
        System.out.println(Thread.currentThread()+ "--总共取了：" + drawTotall);
		}
}
}
