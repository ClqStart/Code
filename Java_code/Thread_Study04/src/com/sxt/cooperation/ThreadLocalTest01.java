package com.sxt.cooperation;

public class ThreadLocalTest01 {
	
//	private static ThreadLocal<Integer> threadLocal = new  ThreadLocal<>();
	
	//匿名内部类
/*	private static ThreadLocal<Integer> threadLocal = new  ThreadLocal<>() {
		protected Integer initialValue() {
			
			return 200;
		};
	};
*/
	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->200);
	
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+"---->"+threadLocal.get());
	
		//设置值
		threadLocal.set(99);
		System.out.println(Thread.currentThread().getName()+"---->"+threadLocal.get());
		
	}
	
	
}
