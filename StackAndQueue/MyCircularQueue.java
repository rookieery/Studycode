package StackAndQueue;

public class MyCircularQueue {
    private int front;
    private int rear;
    private int[] elem;
    private int usedSize;
    private  int DEFAULT_CAPACITY;
    public MyCircularQueue(int k) {
        this.elem = new int[k+1];
        DEFAULT_CAPACITY = k+1;
        this.front = 0;
        this.rear = 0;
    }
    //从rear开始插入
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        this.elem[this.rear] = value;
        this.rear = (this.rear+1)%DEFAULT_CAPACITY;
        this.usedSize++;
        return true;
    }
    //从front开始删除
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        this.front = (this.front+1)%DEFAULT_CAPACITY;
        this.usedSize--;
        return true;
    }
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return this.elem[this.front];
    }
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return this.elem[(this.rear+DEFAULT_CAPACITY-1)%DEFAULT_CAPACITY];
    }
    public boolean isEmpty() {
        return this.front == this.rear;
    }
    public boolean isFull() {
        if((this.rear+1)%DEFAULT_CAPACITY == this.front){
            return true;
        }
        return false;
    }
}
