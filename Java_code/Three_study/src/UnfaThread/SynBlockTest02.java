package UnfaThread;

import java.util.ArrayList;
import java.util.List;

/**
 *1 .操作容器
 *2.同步块
 * @author clq
 *
 */

public class SynBlockTest02 {
	
	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList<>();
		
		for(int i =0;i<10000;i++) {
			new Thread(()->{
				synchronized (list) {
				list.add(Thread.currentThread().getName());
				}
				}).start();
		}
		Thread.sleep(500);
		System.out.println(list.size());
	}
}
