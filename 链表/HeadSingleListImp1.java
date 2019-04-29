package 链表;

import 链表.ICLinked;

public class HeadSingleListImp1 implements ICLinked {
   class Node {
       private int data;
       private Node next;
       public Node() {
           this.data = -1;
       }
       public Node(int data) {
           this.data = data;
       }
   }
   private Node head;
   public HeadSingleListImp1() {
       this.head = new Node();
       this.head.next = this.head;
   }
    @Override
    public void addFirst(int data) {
        Node node = new Node(data);
        node.next = this.head.next;
        this.head.next = node;
    }

    @Override
    public void addLast(int data) {
       Node node = new Node(data);
        Node cur = this.head;
       for(;cur.next != this.head;cur = cur.next) {

       }
       /*Node cur = this.head;
       while (cur.next != this.head) {
           cur = cur.next;
       }*/
       node.next = this.head;
        cur.next = node;
    }
    private Node searchIndex(int index) {
        Node cur = this.head.next;
        for(int count = 0;count < index - 1;count++) {
            cur = cur.next;
        }
        return cur;
    }
    private void checkIndex(int index) {
        if (index < 0 || index > getLength()) {//等于长度就是尾插
            throw new UnsupportedOperationException("Index不合法");
        }
    }

    @Override
    public boolean addindex(int index, int data) {
        checkIndex(index);
        if (index == 0) {
            addFirst(data);
            return true;
        }
       Node pre = searchIndex(index);
       Node node = new Node(data);
       node.next = pre.next;
       pre.next = node;
        return true;
    }

    @Override
    public boolean contains(int key) {
       for (Node cur = this.head.next;cur != this.head;cur = cur.next) {
           if (cur.data == key) {
               return true;
           }
       }
       /*Node cur = this.head.next;//head.data == -1
       while (cur != this.head) {
           if (cur.data == key) {
               return true;
           }
           cur = cur.next;
       }*/
        return false;
    }
    private Node searchPre(int key){
       for (Node pre = this.head;pre.next != this.head;pre = pre.next) {
           if (pre.next.data == key) {
               return pre;
           }
       }
      /* Node pre = this.head;
       while (pre.next != this.head) {
           if (pre.next.data == key) {
               return pre;
           }
           pre = pre.next;
       }*/
       return null;
    }

    @Override
    public int remove(int key) {
       Node pre = searchPre(key);
        if(pre == null) {
            throw new UnsupportedOperationException("没有" +
                    "key这个关键字");
        }
        int oldData = pre.next.data;
       pre.next = pre.next.next;
        return oldData;
    }

    @Override
    public void removeAllkey(int key) {
       Node pre = this.head;
       Node cur = pre.next;
       while (cur != this.head) {
           if (cur.data == key) {
               pre.next = cur.next;
               cur = pre.next;
           }
           else {
               pre = cur;
               cur = cur.next;
           }
       }
    }

    @Override
    public int getLength() {
        int count = 0;
        for(Node cur = this.head.next;cur != this.head;cur = cur.next) {
            count ++;
        }
       /* Node cur = this.head.next;//长度不算头结点
        while (cur != this.head) {
            count++;
            cur = cur.next;
        }*/
        return count;
    }

    @Override
    public void display() {
        for (Node cur = this.head.next;cur != this.head;cur = cur.next) {
            System.out.print(cur.data+" ");
        }
        System.out.println();
      /* Node cur = this.head.next;//长度不算头结点
       while (cur != this.head) {
           System.out.print(cur.data+" ");
           cur = cur.next;
       }
        System.out.println();*/
    }

    @Override
    //作用：防止内存泄漏
    public void clear() {
       while (this.head.next != this.head) {
           Node cur = this.head.next;
           this.head.next = cur.next;
       }
       this.head = null;
    }
}
