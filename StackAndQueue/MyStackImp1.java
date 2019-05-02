package StackAndQueue;

public class MyStackImp1 implements IMyStack {
    private int top;
    private int[] elem;
    private static final int DEFAULT_SIZE = 10;
    public MyStackImp1() {
        this.elem = new int[DEFAULT_SIZE];
        this.top = 0;
    }
    @Override
    public void push(int item) {
        if (this.top == DEFAULT_SIZE) {
            throw new UnsupportedOperationException("栈为满");
        }
        this.elem[this.top] = item;
        this.top++;
    }

    @Override
    public int pop() {
        if (this.top == 0) {
            throw new UnsupportedOperationException("栈为空");
        }
        int p = this.elem[this.top - 1];
        this.top--;
        return p;
    }

    @Override
    public int peek() {
        if (this.top == 0) {
            throw new UnsupportedOperationException("栈溢出");
        }
        int p = this.elem[this.top - 1];
        return p;
    }

    @Override
    public boolean empty() {
        if (this.top == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.top;
    }
    public void display() {
        for (int i = 0; i < this.top; i++) {
            System.out.print(this.elem[i]+" ");
        }
        System.out.println("");
    }
    public void clear() {
        for (int i = 0; i < this.top ; i++) {
            this.elem[i] = 0;
        }
        this.top = 0;
    }
}
