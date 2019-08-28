package cn.txt.io;

import java.io.File;

public class ioTest02 {
	public static void main(String[] args) {
		File dir = new File("D:/BaiduNetdiskDownload");
		
		//list 返回下级名称
		String[]  subNames = dir.list();
		for(String s : subNames) {
			System.out.println(s);
		}
		//listFiles()返回下级对象
		
		File[] subFiles = dir.listFiles();
		for(File s: subFiles) {
			System.out.println(s.getAbsolutePath());
		}
		
		//所有盘符
		File[] root = dir.listRoots();
		for(File s: root) {
			System.out.println(s.getAbsolutePath());
			
		}
		
		printName(dir,0);
}
	
	public static void printName(File src , int level) {
		System.out.println(src.getName());
		for(int i=0; i<level;i++) {
			System.out.print("-");
		}
		
		if(null==src||!src.exists()) {
			return;
		}
		else if(src.isDirectory()) {
			for(File s: src.listFiles()) {
				printName(s,level+1);
			}
		}
		
	}
	
	
}
