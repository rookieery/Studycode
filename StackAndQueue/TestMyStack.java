package StackAndQueue;

public class TestMyStack {
    private MyQueuelmp1 myQueuelmp1;
    private MyQueuelmp1 myQueuelmp2;
    private int usedSize;
    public TestMyStack() {
        this.myQueuelmp1 = new MyQueuelmp1();
        this.myQueuelmp2 = new MyQueuelmp1();
        this.usedSize = 0;
    }
    public void push(int x) {
        if (!myQueuelmp1.empty()) {
            myQueuelmp1.add(x);
        }
        else if (!myQueuelmp2.empty()) {
            myQueuelmp2.add(x);
        }
        else {
            myQueuelmp1.add(x);
        }
        this.usedSize++;
    }
    public int pop() {
        if(empty()){
            return -1;
        }
        int data = 0;
        if(!myQueuelmp1.empty()){
            for (int i = 0; i < this.usedSize - 1;i++) {
                myQueuelmp2.add(myQueuelmp1.poll());
            }
            data = myQueuelmp1.poll();
        }
        else  {
            for (int i = 0; i < this.usedSize - 1;i++) {
                myQueuelmp1.add(myQueuelmp2.poll());
            }
            data = myQueuelmp2.poll();
        }
        this.usedSize--;
        return data;
    }
    public int top() {
        if(empty()){
            return -1;
        }
        int data = 0;
        if(!myQueuelmp1.empty()){
            for (int i = 0; i < this.usedSize;i++) {
                data = myQueuelmp1.poll();//一定得放在循环的第一句
                myQueuelmp2.add(data);
            }
        }
        else  {
            for (int i = 0; i < this.usedSize;i++) {
                data = myQueuelmp2.poll();
                myQueuelmp1.add(data);
            }
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
/*class MyStack {
    private Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }
//一个非常神奇的倒着放入，顺着提出
    public void push(int x) {
        int n = queue.size();
        queue.add(x);
        for (int i = 0; i < n; i++) {
            queue.add(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}*/
