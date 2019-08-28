/**
 * 文档注释
 * @author clq
 *
 */

/*多行注释*/

//单行注释 
import java.math.*;
import java.util.Scanner;
public class TestComment {
	
	public static void main(String[] asrgs) {
		System.out.println("hello world");
	BigDecimal bd2 = BigDecimal.valueOf(0.1);
	BigDecimal bd3 = BigDecimal.valueOf(1.0/10.0);
	System.out.println(bd2.equals(bd3));
	
	char a='T';
	char b ='c';
	System.out.println(""+a+b);
	System.out.println(a+b);
	
	int c=3;
	int d=4;
	System.out.println(c&d);
	System.out.println(c|d);
	System.out.println(c^d);
	System.out.println(~c);
	System.out.println(12>>1);
	System.out.println(c<<2);
	
	
	Scanner canner = new Scanner(System.in);
	System.out.println("请输入你的名字：");
	String name = canner.nextLine();

	System.out.println("请输入你的爱好:");
	String  favor = canner.nextLine();
	
	System.out.println("请输入你的年龄");
	String  age = canner.nextLine();
	
	System.out.println(name);
	System.out.println(favor);
	System.out.println(age);
	
	
}
	
	}
