package UnfaThread;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import java.util.List;

/**
 *1 .操作容器
 *2.同步块
 * @author clq
 *
 */

public class SynBlockListTest3 {
	
	public static void main(String[] args) throws InterruptedException {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		
		for(int i =0;i<10000;i++) {
			new Thread(()->list.add(Thread.currentThread().getName())).start();
		}
		Thread.sleep(500);
		System.out.println(list.size());
	}
}
