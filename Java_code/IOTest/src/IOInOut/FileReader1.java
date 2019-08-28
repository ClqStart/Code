package IOInOut;
/**
 * 
 * 文件字符流
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileReader1 {
 public static void main(String[] args) {
	File src = new File("abc.txt");
	
	Reader is  = null;
	
	
	try {
		is = new FileReader(src);
		char[] flush = new char[1024];
		int len =-1;
		while((len =is.read(flush))!=-1) {
			String sb = new String(flush);
			
			System.out.println(sb);
		}
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		
			try {
				if(is !=null) 
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
}
