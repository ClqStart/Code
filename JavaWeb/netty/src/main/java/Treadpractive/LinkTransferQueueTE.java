package Treadpractive;

import java.util.concurrent.LinkedTransferQueue;

/*
 *@author:C1q
 */
public class LinkTransferQueueTE {

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        LinkedTransferQueue<Character> queue = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                for (char c : aI) {
                    queue.transfer(c);
                    System.out.print(queue.take());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                for (char c : aC) {
                    System.out.print(queue.take());
                    queue.transfer(c);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t2").start();



    }
}
