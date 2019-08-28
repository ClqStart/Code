import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;




public class CIOTest {
	public static void main(String[] args) {
		
	long len = FileUtils.sizeOf(new File("E:\\Ecllpse\\Io_Study\\src\\CIOTest.java"));
	System.out.println(len);	
	
	len = FileUtils.sizeOf(new File("E:\\Ecllpse\\Io_Study"));
	
	System.out.println(len);
	//去除空的文件夹   DirectoryFileFilter.INSTANCE 进行递归遍历子孙集
	Collection<File> files = FileUtils.listFiles(new File("E:\\\\Ecllpse\\\\Io_Study"), EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
	
	for(File file:files) {
		System.out.println(file.getAbsolutePath());
	}
	
	//指定后缀 
Collection<File> files1 = FileUtils.listFiles(new File("E:\\Ecllpse\\Io_Study"),FileFilterUtils.or( new SuffixFileFilter("java"),EmptyFileFilter.NOT_EMPTY ), DirectoryFileFilter.INSTANCE);
	
	for(File file:files1) {
		System.out.println(file.getAbsolutePath());
	}
	
	}
	
	

}
