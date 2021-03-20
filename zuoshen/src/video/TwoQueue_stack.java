package video;

import java.util.LinkedList;
import java.util.Queue;

//两个队列实现栈的功能
//用两个队列相互互换
public class TwoQueue_stack {
    private Queue<Integer> data;
    private Queue<Integer> help;

    public  TwoQueue_stack(){
        this.data = new LinkedList<Integer>();
        this.help = new LinkedList<Integer>();
    }

    public  void push( int num){
        data.add(num);
    }
    public  int  peek(){
        if(data.isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        while (data.size() != 1){
            help.add(data.poll());
        }
        int res = data.poll();
        help.add(res);
        swap(data,help);
        return res;
    }
    public int pop(){
        if(data.isEmpty()){
            throw new RuntimeException("Stack is empty");
        }
        while (data.size() > 1){
            help.add(data.poll());
        }
        int res = data.poll();
        swap(data,help);
        return res;
    }

    private void swap(Queue<Integer> data, Queue<Integer> help) {
        Queue<Integer> tmp = help;
        help = data;
        data = tmp;
    }

}
