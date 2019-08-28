
public class TDownload extends Thread {
	private  String url;
	private String name;
	
	public TDownload(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}
	
	public void run() {
		WebDownLoade wd = new WebDownLoade();
		wd.download(url, name);
	}
	
	public static void main(String[] args) {
		TDownload  td1 = new TDownload("", "");
		TDownload  td2 = new TDownload("", "");
		TDownload  td3 = new TDownload("", "");
	
		
		td1.start();
		td2.start();
		td3.start();
	}
}
