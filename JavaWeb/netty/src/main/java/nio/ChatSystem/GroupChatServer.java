package nio.ChatSystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/*
 *@author:C1q
 */
public class GroupChatServer {

    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int port = 6667;

    //构造器
    //初始化工作
    public GroupChatServer() {
        try {

            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();

            listenChannel.socket().bind(new InetSocketAddress(port));

            listenChannel.configureBlocking(false);

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listen() {
        System.out.println("监听线程: " + Thread.currentThread().getName());

        try {

            while (true) {

                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);

                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getLocalAddress() + "  上线");
                        }
                        if (key.isReadable()) {
                            readData(key);
                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待。。。。。。。");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {

            channel = (SocketChannel) key.channel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = channel.read(buffer);

            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println("from 客户端" + msg);
                //向其他客户端转发信息(去掉自己)
                sendInfoToItherClients(msg, channel);

            }

        } catch (Exception e) {

            try {
                System.out.println(channel.getRemoteAddress() + " 离线了..");
                //取消注册
                key.cancel();
                //关闭通道
                channel.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                ;
            }
        }
    }


    private void sendInfoToItherClients(String msg, SocketChannel self) throws IOException {


        System.out.println("服务器转发信息。。。");

        for (SelectionKey key : selector.keys()) {

            Channel targetChannel = key.channel();

            if (targetChannel instanceof SocketChannel && targetChannel != self) {


                SocketChannel dest = (SocketChannel) targetChannel;

                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

                dest.write(buffer);
            }

        }
    }

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }


}
