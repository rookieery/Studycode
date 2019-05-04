package StackAndQueue;

public class TestMyQueue {
    private MyStackImp1 myStackImp1;
    private MyStackImp1 myStackImp2;
    private int usedSize;
    public TestMyQueue() {
        this.myStackImp1 = new MyStackImp1();
        this.myStackImp2 = new MyStackImp1();
        this.usedSize = 0;
    }
    public void push(int x) {
        myStackImp1.push(x);
        this.usedSize++;
    }
    public int pop() {
        if (empty()) {
            return -1;
        }
        int data = 0;
        if (myStackImp2.empty()) {
            for (int i = 0; i < this.usedSize; i++) {
                myStackImp2.push(myStackImp1.pop());
            }
            data = myStackImp2.pop();
        }
        else {
            data = myStackImp2.pop();
        }
        this.usedSize--;
        return data;
    }
    public int peek() {
        if (empty()) {
            return -1;
        }
        int data = 0;
        if (myStackImp2.empty()) {
            for (int i = 0; i < this.usedSize; i++) {
                myStackImp2.push(myStackImp1.pop());
            }
            data = myStackImp2.peek();
        }
        else {
            data = myStackImp2.peek();
        }
        return data;
    }
    public boolean empty() {
        return this.usedSize == 0;
    }
    public int size() {
        return this.usedSize;
    }
}
