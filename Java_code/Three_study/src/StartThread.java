
public class StartThread extends Thread {
	
	public void run() {
		for(int i =0;i<20;i++) {
			System.out.println("一边听歌");
		}
	}
	
	public static void main(String[] args) {
		
		StartThread st = new StartThread();
		st.start();
		
		for(int i =0;i<20;i++) {
			System.out.println("一边coding");
		}
	}
}
	
