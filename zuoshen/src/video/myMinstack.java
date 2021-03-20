package video;

import java.util.Stack;

public class myMinstack {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;

    public  myMinstack(){
        dataStack  = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int num){
        if(dataStack.isEmpty()){
            minStack.push(num);
        }else if(minStack.peek()<= num){
            minStack.push(minStack.peek());
        }else{
            minStack.push(num);
        }
        dataStack.push(num);
    }
    public  int pop(int num){
        if(dataStack.isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }else {
            minStack.pop();
           return dataStack.pop();
        }

    }

    public  int  getMin(){
        if(dataStack.isEmpty()){
            throw new IllegalArgumentException("stack is empty");
        }else {
            return  minStack.peek();
        }
    }



}
