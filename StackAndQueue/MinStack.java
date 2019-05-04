package StackAndQueue;
/*
private Deque<Integer> myStackImp1;
private Deque<Integer> minStack;
public MyQueue() {
        myStackImp1 = new LinkedList<>();
        minStack = new LinkedList<>();
        }
*/
public class MinStack {
    private MyStackImp1 myStackImp1;
    private MyStackImp1 minStack;
    public MinStack() {
        this.myStackImp1 = new MyStackImp1();
        this.minStack = new MyStackImp1();
    }
    public void push(int x) {
        if (myStackImp1.empty() && minStack.empty()) {
            myStackImp1.push(x);
            minStack.push(x);
        }
        else {
            myStackImp1.push(x);
            if (x <= minStack.peek()) {
                minStack.push(x);
            }
        }
    }
    public void pop() {
        /*if (!myStackImp1.empty()) {
            int data = myStackImp1.pop();
            if (data == minStack.peek()) {
                minStack.pop();
            }
        }*/
        if (!myStackImp1.empty()) {
            if (myStackImp1.peek() == minStack.peek()) {
                myStackImp1.pop();
                minStack.pop();
            } else {
                myStackImp1.pop();
            }
        }
    }
    public int top() {
        if (myStackImp1.empty()) {
            return -1;
        }
        else {
            return myStackImp1.peek();
        }
    }
    public int getMin() {
        return minStack.peek();
    }
}
