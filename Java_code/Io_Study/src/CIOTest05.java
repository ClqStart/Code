import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class CIOTest05 {
  public static void main(String[] args) throws IOException {
	  //复制文件
	  //FileUtils.copyFile(new File("timg1.jpg"),new File("p.jpg"));
	//复制文件到目录
	//FileUtils.copyFileToDirectory (new File("p.jpg"),new File("lib"));
	  
	  //复制目录到目录变成子目录
	 // FileUtils.copyDirectoryToDirectory(new File("lib"), new File("lib2"));
	  
	  //复制目录到目录
	  //FileUtils.copyDirectory(new File("lib"), new File("lib2"));
	  
	  //String url ="图片url";
	  //FileUtils.copyURLToFile(new URL(), new File("mavel.jpg"));

	  String datas = IOUtils.toString(new URL("http://www.baidu.com"),"UTF-8");
	  System.out.println(datas);
	  
	  String data = IOUtils.toString(new URL("http://www.163.com"),"gbk");
	  System.out.println(data);
  }
}
