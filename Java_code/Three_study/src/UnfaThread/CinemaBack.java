package UnfaThread;

import java.util.ArrayList;
import java.util.List;



public class CinemaBack {
	public static void main(String[] args) {
		 List<Integer> avilible= new ArrayList<Integer>();
		 avilible.add(1);
		 avilible.add(2);
		 avilible.add(3);
		 avilible.add(4);
		 avilible.add(5);
		 avilible.add(6);
		 avilible.add(7);
		 
		 LCinema C = new LCinema(avilible, "万达");
		 
		 List<Integer> seats1 = new ArrayList<Integer>();
		 seats1.add(1);
		 seats1.add(2);
		 seats1.add(3);
		 List<Integer> seats2 = new ArrayList<Integer>();
		 seats2.add(7);
		 seats2.add(8);
		 
		new Thread(new LCustomer(C, seats1),"瞎飞").start();
		new Thread(new LCustomer(C, seats2),"kk").start();
		 
	
	}

}




class LCustomer implements Runnable{
	private LCinema  cinima;
	private List<Integer> seats;
	public LCustomer(LCinema cinima, List<Integer> seats) {
		this.cinima = cinima;
		this.seats = seats;
	}
	
	@Override
	public void run() {
	synchronized(cinima){
		boolean flag = cinima.bookTicket(seats);
		if(flag) {
			System.out.println("出票成功"+Thread.currentThread().getName()+"--位置为"+this.seats);
		}else {
			System.out.println("出票失败"+Thread.currentThread().getName()+"--位置不够");
		}
		
	}
	
}



class LCinema{
	private List<Integer> avilible;
	private String name;
	
	
	public LCinema(List<Integer> avilible, String name) {
		this.avilible = avilible;
		this.name = name;
	}





	public boolean bookTicket(List<Integer> seats) {
		System.out.println("可用的位置为"+this.avilible);
		List<Integer> copy = new  ArrayList<Integer>();
		copy.addAll(this.avilible);
		
		copy.removeAll(seats);
		if(avilible.size() -copy.size() !=seats.size()) {
			return false;
		}else {
			this.avilible = copy;
			return true;
		}
	}
}