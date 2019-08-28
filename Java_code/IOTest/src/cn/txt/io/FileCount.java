package cn.txt.io;
/**
 * 
 * 统计文件的的数量
 * 面向对象的的方法
 * 和普通方法
 */
import java.io.File;

public class FileCount {
	public static void main(String[] args) {
		File  src = new File("D:/BaiduNetdiskDownload");
		count(src);
		System.out.println(len);
		
		DirCount dir = new DirCount("D:/BaiduNetdiskDownload");
		System.out.println(dir.getLen1());
		
	}
	
	private static  long  len =0;
	public static void count(File src) {
		if(null != src && src.exists()) {
			if(src.isFile()) {
				len += src.length();
			}
			else {
				for(File s: src.listFiles()){
					count(s);
				}
			}
		}
	}
}
class DirCount{
	private long len1;
	private String path;
	private File src;
	
	public DirCount(String path) {
		this.path = path;
		this.src = new File(path);
		count( this.src);
	}
	private  void count(File src) {
		if(null != src && src.exists()) {
			if(src.isFile()) {
				len1 += src.length();
			}
			else {
				for(File s: src.listFiles()){
					count(s);
				}
			}
		}
	}
	public long getLen1() {
		return len1;
	}
	public void setLen1(long len1) {
		this.len1 = len1;
	}
	
}
