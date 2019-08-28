package IOInOut;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
/**
 * 1.字符流的形式操作字节流
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;

public class CovertTest03 {
	public static void main(String[] args) {
		try ( BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new URL("http://www.baidu.com").openStream(),"UTF-8"));
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream("baidu.html"),"UTF-8"));) {
				
			
			String msg;
				while((msg=reader.readLine())!=null){

						writer.write(msg);
						writer.newLine();
					}
					writer.flush();
				}catch(IOException e) {
					System.out.println("操作异常");
					
				}
		}
	}




