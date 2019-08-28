package IOInOut;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 1图片读取到字节数组
 * 2字节数组读取到文件
 * @author clq
 *
 */
public class IO应用 {
 public static void main(String[] args) {
	 byte[]  datas =fileToArray("timg.jpg");
	 System.out.println(datas.length);
	 byteArrayToFile(datas,"p-byte.png");
}
 /**
  * 1.图片读取到程序  FileInputStream
  * 2.程序读取带字节数组 ByteArrayOutputStream
  */
 public static byte[] fileToArray(String filePath) {
	 //1
	 
	 File src = new File(filePath);
	 byte[] desk = null;
	 
		//2
		InputStream is =null;
		ByteArrayOutputStream baos =null;
		
		//3
		try {
			
			baos = new ByteArrayOutputStream();
			is =  new FileInputStream(src);
			
			byte[] flush =new byte[1024*10];
			int len=-1;
			while(( len =is.read(flush))!= -1) {
				baos.write(flush,0,len);			
			}
			baos.flush();
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {if(null !=null) {
				is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	 
 }
 /**
  * 1字节数组到程序ByteArrayInputStream
  * 2.程序到文件  FileOutputStream
  * 
  * @param src
  * @param filePath
  */
 public static void byteArrayToFile(byte[] src, String filePath) {
			
			File dest = new File(filePath);
			
			
			InputStream is =null;
			OutputStream os =null;
			
		  
		  try {
			 is = new ByteArrayInputStream(src);
			 os = new FileOutputStream(dest);
			
			byte[] flush = new byte[5];
			int len =-1;
			while((len = is.read(flush)) != -1) {
				
				os.write(flush,0,len);
			
			}
			os.flush();
		} catch (FileNotFoundException e) {
					e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {if(null !=os) {
				os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
					}
				}
		  }
 		
 }
		  
 

