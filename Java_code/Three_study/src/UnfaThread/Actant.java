package UnfaThread;

public class Actant {
	public static void main(String[] args) {
		
	
	Account a1 = new Account("结婚彩礼",100);
	drawMoney t1 = new drawMoney(a1,80);
	
	drawMoney t2 = new drawMoney(a1,70);
	
	t1.setName("老婆");
	t2.setName("老子");
	t1.start(); // 你取钱
	t2.start(); // 你老婆取钱
	
	}	
	
}
class Account {
	String name;
	int money;
	
	public Account(String name,int money) {
		this.money = money;
		this.name = name;
		
	}

	}
	

class drawMoney  extends Thread{
	
	


	Account  account;
	int drawMoney;
	int drawTotall;

	public drawMoney(Account account, int drawMoney) {
		super();
		this.account = account;
		this.drawMoney = drawMoney;
	}


	@Override
	public void run() {
		if(account.money<0) {
			return;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		account.money -=drawMoney;
		drawTotall +=drawMoney;
		System.out.println(this.getName() + "--账户余额：" + account.money);
        System.out.println(this.getName() + "--总共取了：" + drawTotall);
		
		
	}
	
}
	
