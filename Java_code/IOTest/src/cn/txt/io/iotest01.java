package cn.txt.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class iotest01 {
	public static void main(String[] args) {
		fileRead();
		
	}
	public static void fileRead() {
		FileInputStream  fl =null;
		try {
		    fl= new FileInputStream("d:/dd.txt");
			StringBuilder sb = new StringBuilder();
			int temp = 0;
			
			while((temp=fl.read()) !=-1) {
				sb.append((char)temp);
			}
			System.out.println(sb);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fl!= null)
				try {
					fl.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
}
