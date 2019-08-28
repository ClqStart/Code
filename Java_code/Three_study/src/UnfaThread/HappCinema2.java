package UnfaThread;

import java.util.ArrayList;
import java.util.List;

public class HappCinema2 {
	public static void main(String[] args) {
		
		List<Integer> available  = new ArrayList<Integer>();
		available.add(1);
		available.add(2);
		available.add(4);
		available.add(5);
		available.add(6);
		Cinema1 c = new Cinema1 (available,"txt");
		
		List<Integer> seat1  = new ArrayList<Integer>();
		seat1.add(1);
		seat1.add(2);
		List<Integer> seat2 = new ArrayList<Integer>();
		seat2.add(5);
		seat2.add(7);
		
		new Thread(new HappyCustomer(c, seat1),"小飞").start();
		
		new Thread(new HappyCustomer(c,seat2),"达菲").start();
	}
	

}
   //顾客 

class HappyCustomer implements Runnable{
	Cinema1  cinema1;
	List<Integer> seats;
	
	public HappyCustomer(Cinema1 cinema, List<Integer> seats) {
		super();
		this.cinema1 = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		{
			boolean flag = cinema1.bookTickets(seats);
			if(flag) {
				System.out.println("出票成功"+Thread.currentThread().getName()+"-<位置为："+seats);
				
			}else{
				System.out.println("出票失败"+Thread.currentThread().getName()+"-<位置不够");
			}
		}
	}
	
}

//电影院
class Cinema1{
	List<Integer>  available;
	String name;
	public Cinema1(List<Integer> available,String name) {
		this.name = name;
		this.available = available;
	}
	
	
	//购票
	public boolean bookTickets(List<Integer> seats) {
		System.out.println("可用的位置："+available);
		List<Integer> copy = new ArrayList<Integer>();
		copy.addAll(available);
		
		copy.removeAll(seats);
		if(available.size() - copy.size() != seats.size()) {
			return false;
		}else {
			available = copy;
			return true;
		}
		
		
}
	}