package video;

import java.util.Stack;

public class TwoStack_Queue {
    private Stack<Integer> push;
    private Stack<Integer> pop;


    public void TwoQueue_stack() {
        push = new Stack<>();
        pop = new Stack<>();
    }

    public void push(int num) {
        push.add(num);
    }

    public int pop() {
        if (pop.isEmpty()) {
            if (push.isEmpty()) {
                throw new RuntimeException("stack is empty");
            } else {
                while (!push.isEmpty()) {
                    pop.add(push.pop());
                }
                return pop.pop();
            }
        } else {
           return pop.pop();
        }
    }
    public int peek() {
        if (pop.isEmpty()) {
            if (push.isEmpty()) {
                throw new RuntimeException("stack is empty");
            } else {
                while (!push.isEmpty()) {
                    pop.add(push.pop());
                }
                return pop.peek();
            }
        } else {
            return pop.peek();
        }
    }
}
