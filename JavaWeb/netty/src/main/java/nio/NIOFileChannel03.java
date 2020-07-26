package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 *@author:C1q
 */
public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("1.txt");
        FileChannel fileInputStreamChannel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");

        FileChannel fileOutputStreamChannel = fileOutputStream.getChannel();

        while (true){
            byteBuffer.clear();
            int read = fileInputStreamChannel.read(byteBuffer);
            if(read==-1){
                break;
            }
            byteBuffer.flip();
            fileOutputStreamChannel.write(byteBuffer);
        }
        fileOutputStream.close();
        fileInputStream.close();


    }
}
