package Practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharaData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {

        lock.lock();
        try {
            //1.判断
            while (number != 0) {
                condition.await();
            }
            //2.生产
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.唤醒
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception {

        lock.lock();
        try {
            //1.判断
            while (number == 0) {
                condition.await();
            }
            //2.生产
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.唤醒
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}


public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        SharaData sharaData = new SharaData();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    sharaData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "AA").start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread (() -> {
                try {
                    sharaData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "BB").start();

        }
    }
}
