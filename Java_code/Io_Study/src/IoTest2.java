import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class IoTest2 {

	public static void main(String[] args) throws IOException {
		//读取文件
		String msg = FileUtils.readFileToString(new File("aa.txt"),"UTF-8");
		System.out.println(msg);
		
		
		byte[]  datas = FileUtils.readFileToByteArray(new File("aa.txt"));
		
		System.out.println(datas.length);
		
		//逐行读取
		
		List<String> msgs = FileUtils.readLines(new File("aa.txt"),"UTF-8");
		
		for(String st:msgs) {
			System.out.println(st);
		}
		
		//迭代器
		
		LineIterator it = FileUtils.lineIterator(new File("aa.txt"),"UTF-8");
		while (it.hasNext()) {
			System.out.println(it.nextLine());
		}
	}
}
