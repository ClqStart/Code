package Practice;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

public class MyRefence {
    //强引用
    public static void phanton(){
        Object object1 = new Object();
        Object object2 = object1;
        object1= null;
        System.gc();
        System.out.println(object2);
        System.out.println("#############" + object1);

    }
    //软引用看内存，内存不足进行清理
    public static  void  softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null;
        //System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }
    public static void Week_Memory_NotEnough(){
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(weakReference.get());
    }

    private  static void myHashMap(){
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = Integer.valueOf(1);
        String  value = "HashMap";

        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);
    }

    private  static void myWeekHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String  value = "WeekHashMap";

        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map);
    }
    private static  void  WeekReferceQueue() throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("--------------------------------------------");
        o1=null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
    private static  void  phantanRenfence() throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("--------------------------------------------");
        o1=null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }

    public static void main(String[] args) throws InterruptedException {
//        softRef_Memory_NotEnough();
//        System.out.println("##########################");
//        Week_Memory_NotEnough();
//        myHashMap();
//        System.out.println("--------------------");
//        myWeekHashMap();
             phantanRenfence();
    }

}
