
public class StartRun  implements Runnable {

	
	public void run() {
		for(int i =0;i<20;i++) {
			System.out.println("一边听歌");
		}
	}
	
	public static void main(String[] args) {
		
//		Thread t = new Thread(new StartRun());
//		t.start();
		
		new Thread(new StartRun()).start();
		
		for(int i =0;i<20;i++) {
			System.out.println("一边coding");
		}
	}
}

