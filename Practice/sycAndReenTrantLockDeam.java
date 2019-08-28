package Practice;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 判断  操作 唤醒
 * */
class ShareResurce {
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private int number = 1;


    public void print5() {
        lock.lock();
        try {
            //1
            while (number != 1) {
                    c1.await();
            }
            //2
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3
            number = 2;
            c2.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //1
            while (number != 2) {
                    c2.await();
            }
            //2
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3
            number = 3;
            c3.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //1
            while (number != 3) {
                    c3.await();
            }
            //2
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3
            number = 1;
            c1.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}


public class sycAndReenTrantLockDeam {


    public static void main(String[] args) {
        ShareResurce shareResurce = new ShareResurce();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                shareResurce.print5();
            }, "AA").start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                shareResurce.print10();
            }, "BB").start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                shareResurce.print15();
            }, "CC").start();
        }
    }
}
