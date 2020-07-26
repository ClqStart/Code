package Treadpractive;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 *@author:C1q
 */
public class Lock_condition {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();


        new Thread(() -> {
            try {
                lock.lock();

                for (char c : aI) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                lock.lock();

                for (char c : aC) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
