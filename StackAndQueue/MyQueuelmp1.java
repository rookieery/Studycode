package StackAndQueue;

public class MyQueuelmp1 implements IMyQueue {
    class Node{
        public int data;
        private Node next;
        private Node(int data) {
            this.data = data;
        }
    }
    private Node front;
    private Node rear;
    private int usedSize;
    public MyQueuelmp1() {
        this.front = null;
        this.rear = null;
        this.usedSize = 0;
    }
    @Override
    public boolean empty() {
        return this.usedSize == 0;
    }

    @Override
    public int peek() {
        if (empty()) {
            throw new UnsupportedOperationException("空队列");
        }
        return this.front.data;
    }

    @Override
    public int poll() {
        if (empty()) {
            throw new UnsupportedOperationException("空队列");
        }
        int Oldata = this.front.data;
        if (this.usedSize == 1) {
            this.front = null;
            this.rear = null;
        }
        else {
           this.front = this.front.next;
        }
        this.usedSize--;
        return Oldata;
    }

    @Override
    public void add(int item) {
        Node node = new Node(item);
        if (empty()) {
            this.front = node;
            this.rear = node;
        }
        else {
            this.rear.next = node;
            this.rear = node;
        }
        this.usedSize++;
    }

    @Override
    public int size() {
       return this.usedSize;
    }

}
