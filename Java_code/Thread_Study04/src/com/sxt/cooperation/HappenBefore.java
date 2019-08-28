package com.sxt.cooperation;

public class HappenBefore {
	
		private static int a =0;
		private static  boolean flag =false;
		
		public static void main(String[] args) throws InterruptedException {
			for(int i=0;i<10;i++) {
				
				a =0;
				 flag =false;
				//更改数据
				Thread t = new Thread(()->{
					a=1;
				flag=true;}); 
				
				
				//读取数据
				Thread t1 = new Thread(()->{
					if(flag) {
						a *=1;	
					}
					if(a==0) {
						System.out.println("HanpenBefore"+a);
					}
					
				});
			
			
			t.start();
			t1.start();
			
			t.join();
			t1.join();
		}
	}
}
