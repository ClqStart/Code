package 异常;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.processing.FilerException;

public class Test11 {
  public static void main(String[] args) {
	  FileReader reader =null;
	 try {
		 reader = new FileReader("g:/机器学习网址.txt");
		
	char r = (char) reader.read();
	System.out.println(r);
	 }catch(FilerException e) {
		 e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		try {
			if(reader != null) {
			reader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	 
	 
}
}
