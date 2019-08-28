package IOInOut;
/**
 * 1.字符流的形式操作字节流
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CovertTest {
	public static void main(String[] args) {
		try (BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));) {
				String msg ="";
				while(!msg.equals("exit")){
					msg = read.readLine();
					
					writer.write(msg);
					writer.newLine();
					writer.flush();
				}
				}catch(IOException e) {
					System.out.println("操作异常");
					
				}
		}
	}



