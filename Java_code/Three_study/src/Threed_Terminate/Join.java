package Threed_Terminate;

public class Join {
	public static void main(String[] args) {
		System.out.println("爸爸和儿子买烟的故事");
		new Thread(new Father()).start();
	}
}

class Father extends Thread{
	public void run () {
		System.out.println("想抽烟，没有烟了");
		System.out.println("让儿子去买烟");
		
		Thread t = new Thread(new Son());
		t.start();
		try {
			t.join();
			System.out.println("老爸接过烟");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("儿子走丢了");
		}
		
		
		
	}
}

class Son extends Thread{
	
	public void run() {
		System.out.println("接过老爸的钱出去了");
		System.out.println("路过游戏厅，玩了10秒钟");
		for(int i =0;i<10;i++) {
			System.out.println(i+"秒过去了");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
}