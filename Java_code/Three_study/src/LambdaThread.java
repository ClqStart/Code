
/**
 * 
 * 1.Lambda简化线程（用一次）的使用
 * @author clq
 *
 */
public class LambdaThread  {

	
	static  class Test implements Runnable{ 
		@Override
		public void run() {
			
			for(int i=0;i<20;i++) {
				System.out.println("一边听歌");
			}
		}
	
	}
	
	public static void main(String[] args) {
		
//		new Thread(new Test()).start();
		//局部内部类
	  class Test2 implements Runnable{ 
			@Override
			public void run() {
				
				for(int i=0;i<20;i++) {
					System.out.println("一边听歌");
				}
			}
		
		}
		
	  new Thread(new Test2()).start();
	  
	  //匿名内部类。必须借用接口或者父类
	  
	  new Thread(new Runnable() {
		
		@Override
		public void run() {
			
			for(int i=0;i<20;i++) {
				System.out.println("一边听歌");
			}
			
		}
	}).start();
	  
	
	  //jdk8简化
	  new Thread(()->{
		  for(int i=0;i<20;i++) {
				System.out.println("一边听歌");
				}
	  }).start();
	
	}
}
