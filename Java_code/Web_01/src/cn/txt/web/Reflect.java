package cn.txt.web;

import java.lang.reflect.InvocationTargetException;

public class Reflect {
		public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
			//获取对象
			//1.对象。class
			Iphone  iphone= new Iphone();
			Class clz=iphone.getClass();
			//2.类。class();
			clz = Iphone.class;
			//3.Class.forName("包名。类名")
			clz = Class.forName("cn.txt.web.Iphone");
			
			//创建对象
//			Iphone iphone2 = (Iphone)clz.newInstance();
//			System.out.println(iphone);
			
			Iphone iphone2 = (Iphone)clz.getConstructor().newInstance();
			System.out.println(iphone2);
			
		}
}


class Iphone{
	
	public Iphone(){
		
	}
	
}