package UnfaThread;

public class HappCinema {
	public static void main(String[] args) {
		Cinema c = new Cinema(2, "txt");
		
		new Thread(new Customer(c,2),"小飞").start();
		new Thread(new Customer(c, 1),"达菲").start();
	}
	

}
   //顾客 

class Customer implements Runnable{
	Cinema  cinema;
	int seats;
	
	public Customer(Cinema cinema, int seats) {
		super();
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		synchronized(cinema) {
			boolean flag = cinema.bookTickets(seats);
			if(flag) {
				System.out.println("出票成功"+Thread.currentThread().getName()+"-<位置为："+seats);
				
			}else{
				System.out.println("出票失败"+Thread.currentThread().getName()+"-<位置不够");
			}
		}
	}
	
}

//电影院
class Cinema{
	int  available;
	String name;
	public Cinema(int available,String name) {
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
}