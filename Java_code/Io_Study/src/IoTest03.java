import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class IoTest03 {
		public static void main(String[] args) throws IOException {
			
			//写出到文件
			FileUtils.write(new File("happy.txt"), "学习是快乐的事情\r\n","UTF-8");
			//是否追加文件
			FileUtils.writeStringToFile(new File("happy.txt"), "学习是辛苦的事情\r\n","UTF-8",true);
			FileUtils.writeStringToFile(new File("happy.txt"), "学习是辛苦的事情\r\n","UTF-8",true);
			FileUtils.writeByteArrayToFile(new File("happy.txt"), "学习是幸福的事情\r\n".getBytes("UTF-8"),true);
			
			//写出列表
		List<String> datas = new ArrayList<String>();
		datas.add("马云");
		datas.add("马化腾");
		datas.add("弼马温");
		
		FileUtils.writeLines(new File("happy.txt"),datas,"+++++\r\n",true);
}
		}
