package IOInOut;
/**
 * 1.字符流的形式操作字节流
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class CovertTest02 {
	public static void main(String[] args) {
		try ( InputStreamReader is = new InputStreamReader(new URL("http://www.baidu.com").openStream(),"UTF-8");) {
				int temp;
				while((temp = is.read())!=-1){
					System.out.print((char) temp);
					
				}
				}catch(IOException e) {
					System.out.println("操作异常");
					
				}
		}
	}



