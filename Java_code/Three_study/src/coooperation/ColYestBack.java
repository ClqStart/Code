package coooperation;



/**
 * 1.管道法
 * @author clq
 *
 */
public class ColYestBack {
		public static void main(String[] args) {
			syContain contain = new syContain();
			new custuem(contain).start();
			new product1(contain).start();
		}
}


//消费者
class custuem extends Thread{
	private syContain contain;
	
	public custuem(syContain contain) {
		this.contain = contain;

	}
	@Override
	public void run() {
	for(int i=0;i<10;i++) {
		System.out.println("消费者消费了"+contain.pop().getId()+"馒头");
	}
	}
}
//生产者
class product1  extends Thread{
	private syContain  contain;
	public product1(syContain contain){
		this.contain = contain;
	}
	
	@Override
	public void run() {
	for(int i =0;i<10;i++) {
		System.out.println("生产第"+i+"个馒头");
		contain.push(new meta(i));
	}
	
	}
	
	
}
//缓存区


class syContain{
	private meta[] container = new meta[10];
	int count =0;	
	public synchronized void push(meta bun) {
		if(count == container.length) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		container[count++]= bun;

		this.notifyAll();
	}
	
	public synchronized meta pop () {
		if(count ==0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		meta bun = container[--count];//count对应的位置没有馒头
		this.notifyAll();
		return bun;
	}
	
}

//馒头
class meta{
	private int id;

	public meta(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}