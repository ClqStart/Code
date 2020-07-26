package netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/*
 *@author:C1q
 */
public class NettyByteBuf02 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world!", StandardCharsets.UTF_8);

        if(byteBuf.hasArray()){

            byte[] content = byteBuf.array();

            System.out.println(new String(content, StandardCharsets.UTF_8));
            System.out.println("byteBuf=" + byteBuf);

            System.out.println(byteBuf.arrayOffset()); // 0
            System.out.println(byteBuf.readerIndex()); // 0
            System.out.println(byteBuf.writerIndex()); // 12
            System.out.println(byteBuf.capacity()); // 36

            //System.out.println(byteBuf.readByte()); //readerindex增加
            System.out.println(byteBuf.getByte(0)); // 104

            int len = byteBuf.readableBytes(); //可读的字节数  12
            System.out.println("len=" + len);

            //使用for取出各个字节
            for(int i = 0; i < len; i++) {
                System.out.println((char) byteBuf.getByte(i));
            }

            //按照某个范围读取
            System.out.println(byteBuf.getCharSequence(0, 4, StandardCharsets.UTF_8));
            System.out.println(byteBuf.getCharSequence(4, 6, StandardCharsets.UTF_8));

        }
    }
}

