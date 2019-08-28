
public class TDownRun implements Runnable {
		private  String url;
		private String name;
		
		public TDownRun(String url, String name) {
			super();
			this.url = url;
			this.name = name;
		}
		
		public void run() {
			WebDownLoade wd = new WebDownLoade();
			wd.download(url, name);
		}
		
		public static void main(String[] args) {
			
			new Thread(new TDownRun("", "")).start();
			new Thread(new TDownRun("", "")).start();
			new Thread(new TDownRun("", "")).start();
		}
	}


