package Practice;

public class HellGC extends Object{


    public static void main(String[] args) {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long Maxmemory = Runtime.getRuntime().maxMemory();

        System.out.println(totalMemory + ":\t"+((totalMemory)/1024/1024)+"MB"); //1/64
        System.out.println(Maxmemory + ":\t"+((Maxmemory)/1024/1024)+"MB");// 1/4
    }
}
