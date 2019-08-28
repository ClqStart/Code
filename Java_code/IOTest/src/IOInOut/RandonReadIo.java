package IOInOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandonReadIo {
	
	public static void main(String[] args) throws IOException {
		
		File src = new File("E:\\Ecllpse\\IOTest\\src\\IOInOut\\DataTest.java");
		
		long len = src.length();
		
		int blockSize = 1024;
		
		int size = (int) Math.ceil(len*1.0/blockSize);
		System.out.println(size);
		
		int beginPos =0;
		int actualSize =(int)(blockSize>len? len:blockSize);
		
		for (int i =0;i<size;i++) {
			beginPos = i* blockSize;
			if(i==size-1) {
				actualSize = (int)len;
			}else {
				actualSize = blockSize;
				len -=actualSize;
			}
		
		System.out.println(i+"--->"+beginPos+"--->"+actualSize);
		test2(i,beginPos,actualSize);
	}
		}
	
	public static void test2(int i,int beginPos,int actualSize) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(new File("E:\\Ecllpse\\IOTest\\src\\IOInOut\\DataTest.java"),"r");
		
		raf.seek(2);
		
		byte[] flush = new byte[1024];
		int len =-1;
		
		while((len=raf.read(flush))!= -1){
			System.out.println(new String(flush,0,len));
		}
		
		raf.close();
	}
	

}
