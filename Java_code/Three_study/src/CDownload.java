import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CDownload implements Callable<Boolean> {
		private  String url;
		private String name;
		
		public CDownload(String url, String name) {
			super();
			this.url = url;
			this.name = name;
		}
		
		public Boolean call() throws Exception {
			WebDownLoade wd = new WebDownLoade();
			wd.download(url, name);
			
			System.out.println(name);
			return true;
		}
		
		public static void main(String[] args) throws InterruptedException, ExecutionException {
			CDownload  td1 = new CDownload("", "");
			CDownload  td2 = new CDownload("", "");
			CDownload  td3 = new CDownload("", "");
		
			//1.创建目标对象 
			//2.创建执行任务 
				ExecutorService ser = Executors.newFixedThreadPool(3);
			//3.提交任务  
				Future<Boolean> result = ser.submit(td1);
				Future<Boolean> result1 = ser.submit(td2);
				Future<Boolean> result2 = ser.submit(td3);

			//4.获取结果   
				boolean r1 = result.get();
				boolean r2 = result.get();
				boolean r3 = result.get();

			//5.关闭服务
				ser.shutdownNow();
			
			
			
		}
	}


