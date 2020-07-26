package nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
 *@author:C1q
 */
public class NIOFileChannel01 {

    public static void main(String[] args) throws IOException {
        String  str = "hello,三国古";

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\fileo1.txt");


        //实际FileChannelImpl
        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


        ByteBuffer read = byteBuffer.put(str.getBytes());


        byteBuffer.flip();
        channel.write(byteBuffer);

        fileOutputStream.close();
    }

}
