package com.sxt.cooperation;


/**
 * DCL单例模式 懒汉式
 * 1.构造器私有化-》避免外部new构造器
 * 2.提供私有的静态属性--》储存对象的地址
 * 3.提供公共的静态方法--》获取属性
 * @author clq
 *
 */
public class DoubleCheckedLocking {
	
	//2.提供私有的静态属性
	//防止指令重排     没有可能访问空对象
	private static volatile DoubleCheckedLocking  instance;
	
	//1.构造器私有化-》避免外部new构造器
	private DoubleCheckedLocking() {
		
	}
	//3.提供公共的静态方法--》获取属性
	
	//1.开辟空间 2.初始化对象信息 3.返回对象的地址的引用
	public static DoubleCheckedLocking getInstance() {
		
		if(null !=instance) {   //避免不必要的同步
			return instance;
		}
		synchronized (DoubleCheckedLocking.class) {
			if(null ==instance) {
				instance = new DoubleCheckedLocking();
			}
		}
		
		
		return instance;
	}
	public static void main(String[] args) {
		Thread t = new Thread(()->{System.out.println(DoubleCheckedLocking.getInstance());}); 
		t.start();
		System.out.println(DoubleCheckedLocking.getInstance());
	}
}
