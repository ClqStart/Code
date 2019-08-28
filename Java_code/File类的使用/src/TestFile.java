import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestFile {
	public static void main(String[] args) throws IOException {
		File f = new File("g:/机器学习.txt");
		System.out.println(f);
		f.renameTo(new File("g:/机器学习网址.txt"));
		
		//获取当前目录
		System.out.println(System.getProperty("user.dir"));
		
		File f2 = new File("gg.txt");
		f2.createNewFile();
		
		//文件基本操作
		 System.out.println("File是否存在："+f2.exists());
	        System.out.println("File是否是目录："+f2.isDirectory());
	        System.out.println("File是否是文件："+f2.isFile());
	        System.out.println("File最后修改时间："+new Date(f2.lastModified()));
	        System.out.println("File的大小："+f2.length());
	        System.out.println("File的文件名："+f2.getName());
	        System.out.println("File的目录路径："+f2.getPath());
	        System.out.println("File的目录路径："+f2.getAbsolutePath());
	        
	   //综合应用
	        
	        File file =  new File("./Test");
	        
	        boolean flag = file.exists();
	        if(flag) {
	        	boolean fla = file.delete();
	        	if(fla) {	
	        		System.out.println("删除成功");
	        }else {
	        	System.out.println("删除失败");
	              }
	        }else {
	        	boolean flagn = true;
	        	
	        	try {
	        		File dir = file.getParentFile();
	        		dir.mkdirs();
	        		flagn = file.createNewFile();
	        		System.out.println("创建成功");
	        	}catch(IOException e) {
	        		System.out.println("创建失败");
	        		e.printStackTrace();
	        	}
	        }
		
	}

}
