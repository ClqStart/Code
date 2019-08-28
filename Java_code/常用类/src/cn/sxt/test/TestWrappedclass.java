package cn.sxt.test;

public class TestWrappedclass {
  public static void main(String[] args) {
	  //基本数据转换成包装类对象
	  Integer a = new Integer(3);
	  Integer b = Integer.valueOf(30);
	  
	  // 包装类转换成基本数据类型
	  
	  int c = b.intValue();
	  double d = b.doubleValue();	
	  
	  //字符串转换成包装类
	  Integer e = new Integer("9999");
	  Integer f = Integer.parseInt("9000");
	  
	  //包装类转换成字符串
	  String str = f.toString();
	  
	  System.out.println(Integer.MAX_VALUE);
  }
}
