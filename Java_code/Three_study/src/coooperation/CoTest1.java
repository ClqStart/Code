package coooperation;
/*
 * 1.管道法
 */
public class CoTest1 {
	public static void main(String[] args) {
		SynContainer container = new SynContainer();
		
		new Product(container).start();
		new Consumer(container).start();
	}

}


//生成者
class Product extends Thread{
	SynContainer container;
	
	public Product(SynContainer container) {
		super();
		this.container = container;
	}


	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("生产--》"+i+"馒头");
			container.push(new Steamedbun(i));
		}
	}
}

class Consumer extends Thread{
	SynContainer  container;
	
	public Consumer(SynContainer container) {
		super();
		this.container = container;
	}
	public void run() {
		for(int i=0;i<100;i++) {
			System.out.println("消费--》"+container.pop().id+"馒头");
			
		}
	}
}

//缓存区
class SynContainer{
	Steamedbun[] buns = new Steamedbun[10];
	int count =0;
	//生产
	public synchronized void push(Steamedbun bun) {
		
		if(count ==buns.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buns[count] =bun;
		count ++;
		this.notifyAll();//通知消费
	}
	//获取消费
	public synchronized Steamedbun  pop() {
		if(count ==0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Steamedbun bun = buns[--count];
		this.notifyAll();//存在空间唤醒所有线程
		return bun;
	}
	
}
//物品
class Steamedbun{
	int id;

	public Steamedbun(int id) {
		super();
		this.id = id;
	}
	
}