package Practice;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class  MyThread implements  Runnable{

    @Override
    public void run() {

    }
}

class  Mythread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("#############come in callable");
        return 1024;
    }
}




public class CallableDema {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread t1= new Thread();
        t1.start();

        //适配器原理
        FutureTask<Integer> futureTask = new FutureTask<>(new Mythread2());
        Thread t2 = new Thread(futureTask,"AA");
        Thread t3 = new Thread(futureTask,"BB");
        t2.start();
        int result1 = 100;
        while (! futureTask.isDone()){

        }
        int result2 = futureTask.get(); //要求callable的计算结果，最好放在最后面
        System.out.println("##############result" +(result1+result2));
    }
}
