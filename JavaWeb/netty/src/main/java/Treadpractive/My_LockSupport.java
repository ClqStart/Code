package Treadpractive;

import java.sql.SQLOutput;
import java.util.concurrent.locks.LockSupport;

/*
 *@author:C1q
 */
public class My_LockSupport {

    static  Thread t1= null, t2=null;

    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();


        t1 = new Thread(()->{

            for (char c : aI) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }

        },"t1");

        t2 = new Thread(()->{
            for (char c : aC) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);//叫醒t2
            }

        },"t2");

        t1.start();
        t2.start();

    }
}
