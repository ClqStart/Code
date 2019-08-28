package Practice;




class HoldlOCKThread implements  Runnable{
    private  String locka;
    private  String lockb;

    public HoldlOCKThread(String locka,String lockb){
        this.locka = locka;
        this.lockb = lockb;
    }

    @Override
    public void run() {
        synchronized (this.locka){
            System.out.println(Thread.currentThread().getName() +"持有的锁"+this.locka+"去获取B的锁"+this.lockb);

            synchronized (this.lockb){
                System.out.println(Thread.currentThread().getName() +"持有的锁"+this.lockb+"去获取A的锁"+this.locka);
            }
        }

    }
}


public class DeadLockDeam {
    public static void main(String[] args) {
        String locka = "lockA";
        String lockb = "lockB";
        new Thread(new HoldlOCKThread(locka,lockb),"AAA").start();
        new Thread(new HoldlOCKThread(lockb,locka),"BBB").start();

    }
}
