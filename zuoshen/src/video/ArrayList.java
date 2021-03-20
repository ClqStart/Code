package video;

//用数组实现固定的列表
//push 操作end改变为+1，start不变指向第一个数
//pop弹出第一个数，指向下一个数，end不变，相当于start在追赶end

public class ArrayList {
    private Integer[] arr;
    private int size;
    private int start = 0;
    private int end = 0;

    public ArrayList(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("the initSize is less than 0");
        }
        arr = new Integer[initSize];
        this.size = 0;
        start = 0;
        end = 0;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        return arr[start];
    }

    public void push(int obj) {
        if (size == arr.length) {
            throw new IllegalArgumentException("The queue is full");
        }
        size++;
        arr[end] = obj;
        end = end == arr.length - 1 ? 0: end + 1;
    }

    public Integer pop() {
        if (size == 0) {
            throw new IllegalArgumentException("The queue is empty");
        }
       size--;
       int tmp= arr[start];
       start = start == arr.length-1? 0: start+1;
       return tmp;
    }


}
