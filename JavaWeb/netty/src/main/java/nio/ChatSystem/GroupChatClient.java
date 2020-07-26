package nio.ChatSystem;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/*
 *@author:C1q
 */
public class GroupChatClient {

    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupChatClient() throws IOException {
        selector =Selector.open();

       socketChannel = socketChannel.open(new InetSocketAddress(HOST, PORT));

       socketChannel.configureBlocking(false);

       socketChannel.register(selector, SelectionKey.OP_READ);

        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username +" is ok ....");

    }


    public  void  sendInfo(String  info) throws IOException {
        info =username +" 说 ：" +info;

        socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }

    public void readInfo() {

        try {

            int readChannels = selector.select();
            if(readChannels > 0) {//有可以用的通道

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {

                    SelectionKey key = iterator.next();
                    if(key.isReadable()) {
                        //得到相关的通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        //得到一个Buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        //读取
                        sc.read(buffer);
                        //把读到的缓冲区的数据转成字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                }
                iterator.remove(); //删除当前的selectionKey, 防止重复操作
            } else {
                //System.out.println("没有可以用的通道...");

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        final GroupChatClient chatClient = new GroupChatClient();

        new Thread(){

            public  void  run() {
                chatClient.readInfo();
                try {
                    Thread.currentThread().sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }.start();


        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            chatClient.sendInfo(s);
        }

    }

}
