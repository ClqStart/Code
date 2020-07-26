package bio;

/*
 *@author:C1q
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class BIOServer {

    public static void main(String[] args) throws Exception {

        //创建一个线程池,每连接到一个客户端，就启动一个线程和客户端进行通信
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        ServerSocket server=new ServerSocket(6666);
        System.out.println("tomcat服务器启动...");
        while(true){
            //阻塞， 等待客户端连接
            final Socket socket = server.accept();
            System.out.println("连接到一个客户端！");
            newCachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    //业务处理
                    handler(socket);
                }
            });
        }

    }

    /**
     * 处理
     * @param socket
     */
    public static void handler(Socket socket){
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            while(true){
                //读客户端数据 阻塞
                int read = inputStream.read(bytes);
                if(read != -1){
                    System.out.println(new String(bytes, 0, read));
                    outputStream.write(bytes,0,read);
                    outputStream.flush();
                }else{
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                System.out.println("关闭和client的连接..");
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
