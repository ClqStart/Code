package Practice;

public class Error {

    private static void  JavaHeap_Space(){
        byte[]  bytes = new byte[8*1024*1024];
    }

    public static void main(String[] args) {
        JavaHeap_Space();
    }


}
