package video;


//用数组实现固定的栈
public class ArrayStack {
    private  Integer[] arr;
    private  int size;

    public  ArrayStack( int initSize) {
        if(initSize < 0){
            throw new IllegalArgumentException("the initSize is less than 0");
        }
        arr = new Integer[initSize];
        this.size = 0;
    }

    public Integer peek(){
        if(size ==0){
            return  null;
        }
        return arr[size-1];
    }

    public  void push(int obj){
        if(size == arr.length) {
            throw new IllegalArgumentException("The queue is full");
        }
        arr[size++] = obj;
    }

    public  Integer pop(){
        if(size ==0){
            throw new IllegalArgumentException("The queue is empty");
        }
        return arr[--size];
    }

}
