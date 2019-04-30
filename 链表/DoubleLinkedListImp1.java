package 链表;

import 链表.IDoubleLinked;

public class DoubleLinkedListImp1 implements IDoubleLinked {
    class Node {
        private int data;
        private Node next;
        private Node prve;
        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prve = null;
        }
    }
    private Node head;
    private Node last;
    public DoubleLinkedListImp1() {
        this.head = null;
        this.last = null;
    }
    @Override
    public void addFirst(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
            this.last = node;
        }
        else {
            node.next = this.head;
            this.head.prve = node;
            this.head = node;
        }
    }

    @Override
    public void addLast(int data) {
        Node node = new Node(data);
        if (this.last == null) {
            this.head = node;
            this.last = node;
        }
        else {
            this.last.next = node;
            node.prve = this.last;
            this.last = node;
        }
    }
    private void checkIndex(int index) {
        if (index < 0 || index > getLength()) {//等于长度就是尾插
            throw new UnsupportedOperationException("Index不合法");
        }
    }
    private Node searchIndex(int index) {
        checkIndex(index);
        Node cur = this.head;
        for(int count = 0;count < index;count++) {
            cur = cur.next;
        }
        return cur;
    }

    @Override
    public boolean addindex(int index, int data) {
        if (index == 0) {
            addFirst(data);
            return true;
        }
        if (index == getLength()) {
            addLast(data);
            return true;
        }
        Node node = new Node(data);
        Node cur = searchIndex(index);
        node.next = cur;
        node.prve = cur.prve;
        node.prve.next = node;
        node.next.prve = node;
        return true;
    }

    @Override
    public boolean contains(int key) {
        for (Node cur = this.head;cur != null;cur = cur.next) {
            if (cur.data == key) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int remove(int key) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                int oldData = cur.data;
                if (cur == this.head) {
                    this.head = cur.next;
                    this.head.prve = null;
                }
                else {
                    cur.prve.next = cur.next;
                    if (cur.next != null){
                        cur.next.prve = cur.prve;
                    }
                    else {
                        this.last = cur.prve;
                    }
                }
                return oldData;
            }
            cur = cur.next;
        }
        return -1;
    }

    @Override
    public void removeAllkey(int key) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                if (cur == this.head) {
                    this.head = cur.next;
                    this.head.prve = null;
                }
                else {
                    cur.prve.next = cur.next;
                    if (cur.next != null){
                        cur.next.prve = cur.prve;
                    }
                    else {
                        this.last = cur.prve;
                    }
                }
            }
            cur = cur.next;
        }
    }

    @Override
    public int getLength() {
        int count = 0;
        for(Node cur = this.head;cur != null;cur = cur.next) {
            count ++;
        }
        return count;
    }

    @Override
    public void display() {
        for (Node cur = this.head;cur != null;cur = cur.next) {
            System.out.print(cur.data+" ");
        }
        System.out.println();
    }

    @Override
    public void clear() {
        while (this.head.next != null) {
            Node cur = this.head.next;
            this.head.next = cur.next;
            cur.prve = null;
        }
        this.head = null;
        this.last = null;
    }
}
