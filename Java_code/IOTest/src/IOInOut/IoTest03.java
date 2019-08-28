package IOInOut;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class IoTest03 {
	public static void main(String[] args) {
		
		File src = new File("abc.txt");
		
		InputStream is =null;
		
		
		try {
			is =  new FileInputStream(src);
			
			byte[] flush =new byte[1024];
			int len=-1;
			while(( len =is.read(flush))!= -1) {
				
			//解码操作
			String sb = new String(flush,0,len);
				
			System.out.println(sb);
			
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
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

