package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 *@author:C1q
 */
public class NIOFileChannel04 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("2.jpg");

        FileOutputStream fileOutputStream = new FileOutputStream("1.jpg");

        FileChannel outputStreamChannel = fileOutputStream.getChannel();
        FileChannel InputStreamChannel = fileInputStream.getChannel();

        outputStreamChannel.transferFrom(InputStreamChannel,0,InputStreamChannel.size());

        fileOutputStream.close();
        fileInputStream.close();


    }
}
