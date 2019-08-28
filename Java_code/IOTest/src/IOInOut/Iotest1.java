package IOInOut;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Iotest1 {
   public static void main(String[] args) {
	   //创建源
	   File src = new File("abc.txt");
	   //选择流
	   InputStream is =null;
	   try {
		   is  = new FileInputStream(src);
		//操作
		int data1 = is.read();
		int data2 = is.read();
		int data3 = is.read();		
		
		System.out.println( (char)data1);
		System.out.println( (char)data2);
		System.out.println((char) data3);
		
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	   }finally {
		   try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
   }
   }
