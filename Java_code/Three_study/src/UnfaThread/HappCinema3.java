package UnfaThread;

public class HappCinema3 {
	public static void main(String[] args) {
		Web12306 c = new Web12306(4, "txt");
		
		
		new Passenger(c, "小飞", 2).start();
		new Passenger(c, "高飞", 1).start();
		
	
	}
	

}
   //顾客 

class Passenger extends Thread{
	int seats;
	
	public Passenger(Runnable target , String name, int seats) {
		super(target,name);
		this.seats = seats;
	}

	
}

//电影院
class Web12306 implements Runnable{
	int  available;
	String name;
	public Web12306(int available,String name) {
		this.name = name;
		this.available = available;
	}
	
	
	//购票
	public boolean bookTickets(int seats) {
		System.out.println("可用的位置："+available);
		if(seats>available) {
			return false;
		}
		available -= seats;
		return true;
	}
	@Override
	public void run() {
		Passenger p=(Passenger)Thread.currentThread(); 
			boolean flag = this.bookTickets(p.seats);
			if(flag) {
				System.out.println("出票成功"+Thread.currentThread().getName()+"-<位置为："+p.seats);
				
			}else{
				System.out.println("出票失败"+Thread.currentThread().getName()+"-<位置不够");
			}
		}
	
}