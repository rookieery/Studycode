package 链表;

import 链表.Ilinked;

public class MySingleList implements Ilinked {
    //节点
    class Node{
        public int data;

        public int getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public Node next;
        public Node(int data) {
            this.data = data;
        }
    }
    private Node head;
    public MySingleList(){
        this.head = null;
    }
    @Override
    public void addFirst(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        }
        else {
            node.next = this.head;
            this.head = node;
        }

    }

    @Override
    public void addLast(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        }
       else {
            //for(Node cur = this.head;cur.next != null;cur = cur.next){}
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = node;
        }
    }
    private void checkIndex(int index) {
        if (index < 0 || index > getLength()) {//等于长度就是尾插
            throw new UnsupportedOperationException("Index不合法");
        }
    }
    //返回前面的那个节点
    private Node searchIndex(int index) {
        Node cur = this.head;
        for(int count = 0;count < index - 1;count++) {
            cur = cur.next;
        }
       /* int count = 0;
        while (count < index - 1) {
            cur = cur.next;
            count++;
        }*/
        return cur;
    }
    @Override
    public boolean addindex(int index, int data) {
        checkIndex(index);
        if (index == 0) {
            addFirst(data);
            return true;
        }
        Node node = new Node(data);
        Node cur = searchIndex(index);
        node.next = cur.next;
        cur.next = node;
        return true;
    }

    @Override
    public boolean contains(int key) {
        for (Node cur = this.head;cur != null;cur = cur.next) {
            if (cur.data == key) {
                return true;
            }
        }
        /* Node cur = this.head;
        while (cur != null) {
            if (cur.data == key) {
                return true;
            }
            cur = cur.next;
        }*/
        return false;
    }
    private Node searchPre(int key){
       Node pre = this.head;
       if (pre.data == key) {
           return this.head;
       }
       while (pre.next != null) {
           if (pre.next.data == key) {
               return pre;
           }
           pre = pre.next;
       }
       return null;
    }

    @Override
    public int remove(int key) {
        int oldData = 0;
        Node pre = searchPre(key);
        if(pre == null) {
            throw new UnsupportedOperationException("不存在key节点");
        }
        if(pre == head && pre.data == key) {
            oldData = this.head.data;
            this.head = this.head.next;
            return oldData;
        }
        oldData = pre.next.data;
        pre.next = pre.next.next;
        return oldData;
    }

    @Override
    public void removeAllkey(int key) {
        if (this.head == null) {
            return;
        }
        Node pre = this.head;
        Node cur = pre.next;
        if (pre.data == key) {
            this.head = pre.next;
        }
        while (cur != null) {
            if (cur.data == key){
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
        for(Node cur = this.head;cur != null;cur = cur.next) {
            count ++;
        }
       /* Node cur = this.head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }*/
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
        while (this.head != null) {//把头指针往后移
            Node cur = this.head.next;
            this.head.next = null;
            this.head = cur;
        }
       /* while (this.head.next != null) {
            Node cur = this.head.next;
            this.head.next = cur.next;
        }
        this.head = null;*/
    }
    public Node reverseList1() {
        Node cur = this.head;
        Node pre = null;
        Node reverseHead = null;
        while (cur != null) {
            Node curNext = cur.next;//在cur.next被赋值之前保存好原cur.next的值
            if (curNext == null) {
                reverseHead = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = curNext;
        }
        return reverseHead;
    }
    public Node reverseList() {
        Node pre = this.head;
        Node cur = this.head.next;
        Node las = cur;
        Node reverseHead = null;
        if(cur == null) {
            reverseHead = this.head;
        }
        while (cur != null) {
            if (cur.next == null) {
                reverseHead = cur;
            }
            pre.next = cur.next;
            cur = cur.next;
            las.next = pre;
            pre = las;
            las = cur;
        }
        return reverseHead;
    }
    public void show() {
        Node head = reverseList();
        for (Node cur = head;cur != null;cur = cur.next) {
            System.out.print(cur.data+" ");
        }
        System.out.println();
    }
    public void show1(Node head) {
        for (Node cur = head;cur != null;cur = cur.next) {
            System.out.print(cur.data+" ");
        }
        System.out.println();
    }
    public void show2(Node node) {
        System.out.println(node.data);
    }
    public Node middleNode() {
        Node slow = this.head;
        Node fast = this.head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public Node returnLastk(int k) {
        Node cur = this.head;//不带头
        for (int i = 0; i < getLength() - k; i++) {
            cur = cur.next;
        }
        return cur;
    }
    public Node findKthToTail(int k) {
        Node fast = this.head;
        Node slow = this.head;
        if(fast == null || k <= 0) {
            System.out.println("没有这个节点");
            return null;
        }
        while (k - 1 > 0) {
            if(fast.next != null) {
                fast = fast.next;
                k--;
            }else {
                System.out.println("没有这个节点");
                return null;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    public void removeRepeat() {
        Node cur = this.head;
        while (cur != null) {
            if ( cur.next != null && cur.data == cur.next.data) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
    }
    public void BubbleSort() {
        Node cur = this.head;
        for (int i = 0; i < getLength() - 1; i++) {
            boolean sorted = true;
           while (cur.next != null) {
               Node curNext = cur.next;
               if (cur.data < cur.next.data ) {
                       int t = cur.data;
                       cur.data = curNext.data;
                       curNext.data = t;
               }
               cur = cur.next;
            }
            if (sorted == true) {
               break;
            }
        }
    }
   public Node partition(int n) {
       Node beforeStart = null;
       Node beforeEnd = null;
       Node afterStart = null;
       Node afterEnd = null;
       Node pHead = this.head;
       while (pHead != null) {
           Node pHeadNext = pHead.next;
           pHead.next = null;
           if (pHead.data < n) {
               if (beforeStart == null) {
                   beforeStart = pHead;
                   beforeEnd = pHead;
               }
               else {
                   beforeEnd.next = pHead;
                   beforeEnd = pHead;
               }
           }
           if (pHead.data >= n) {
               if (afterStart == null) {
                   afterStart = pHead;
                   afterEnd = pHead;
               }
               else {
                   afterEnd.next = pHead;
                   afterEnd = pHead;
               }
           }
           pHead = pHeadNext;
       }
       if (beforeStart == null) {
           return afterStart;
       }
       beforeEnd.next = afterStart;
       return beforeStart;
   }
   public void createCycle() {
       Node cur = this.head;
       while(cur.next != null) {
           cur = cur.next;
       }
       cur.next = this.head.next.next.next;
   }
   public boolean hasCycle() {
       Node fast = this.head;
       Node slow = this.head;
       while (fast != null && fast.next != null) {//有环一定会相遇，无环一定会执行到null
           fast = fast.next.next;
           slow = slow.next;
           if (fast == slow) {
               return true;
           }
       }
       return false;
   }
    public Node detectCycle() {
        Node fast = this.head;
        Node slow = this.head;
        while(fast != null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                break;
            }
        }
        if(fast == null || fast.next == null) {
            return null;
        }
        slow = this.head;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    public Node deleteDuplication() {
        Node newHead = new Node(-1);//定义一个虚拟的头结点来串其一个新的链表
        Node tmpHead = newHead;
        Node cur = this.head;
        while (cur != null) {
            if (cur.next != null && cur.data == cur.next.data) {
                //把所有相等的值全部跳过
                while (cur.next != null && cur.data == cur.next.data) {
                    cur = cur.next;
                }
                cur = cur.next;
                newHead.next = cur;//为了能够把末尾的null连接上
            }
            //else里面进行串接
            else {
                newHead.next = cur;
                newHead = cur;//这两部才是串一个节点
                cur = cur.next;
            }
        }
        return tmpHead.next;
    }
    public boolean chkPalindrome() {
        if(this.head == null) {
            return false;
        }
        else if(this.head.next == null) {
            return true;
        }
        Node fast = this.head;
        Node slow = this.head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node p = slow.next;
        if (p != null) {
            Node pNext = p.next;
            while (p != null) {
                p.next = slow;
                slow = p;
                p = pNext;
                if (p != null) {
                    pNext = p.next;
                }
            }
        }
        //此时链表以及反转完成
        //head和this.head有什么不一样？
        //head表示方法参数里的值，this.head表示类里面属性的head
        while(this.head != slow) {
            if(this.head.data != slow.data) {
                return false;
            }
            if(this.head.next == slow) {//此处判断偶，出循环判断奇
                return true;
            }
            this.head = this.head.next;
            slow = slow.next;
        }
        return true;
    }
}
