package Practice;

import java.util.concurrent.CountDownLatch;

public class CountDown_latch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {

            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国，被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();

        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t ###############秦统一六国");
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetcode());
        System.out.println(CountryEnum.ONE.getRetMessage());


    }
}

