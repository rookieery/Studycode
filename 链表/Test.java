package 链表;

public class Test {
   public static MySingleList.Node getIntersectionNode
           (MySingleList.Node headA, MySingleList.Node headB) {
      MySingleList.Node pLong = headA;
      MySingleList.Node pShort = headB;
      int lenA = 0;
      while(pLong != null) {
         lenA++;
         pLong = pLong.next;
      }
      int lenB = 0;
      while(pShort != null) {
         lenB++;
         pShort = pShort.next;
      }
      pLong = headA;
      pShort = headB;

      int myLen = lenA-lenB;

      if(myLen < 0) {
         pLong = headB;
         pShort = headA;
         myLen = lenB-lenA;
      }
      for (int i = 0; i < myLen; i++) {
         pLong = pLong.next;
      }

      //起点相同了
      while (pLong != null && pShort != null ) {
          if (pLong == pShort) {
              return pLong;
          }
          pLong = pLong.next;
          pShort = pShort.next;
      }
      return null;
   }
    public static MySingleList.Node mergeTwoLists(
            MySingleList.Node headA, MySingleList.Node headB) {
        MySingleList mySingleList = new MySingleList();
        MySingleList.Node newHead = mySingleList.new Node(-1);
        MySingleList.Node tmpHead = newHead;
        while (headA != null && headB != null) {
            if (headA.data >= headB.data) {
                newHead.next = headB;
                newHead = headB;
                headB = headB.next;
            }
            else {
                newHead.next = headA;
                newHead = headA;
                headA = headA.next;
            }
        }
        if(headA != null) {
            newHead.next = headA;
        }
        if(headB != null) {
            newHead.next = headB;
        }
        return tmpHead.next;
    }
   public static void main(String[] args) {
       MySingleList mySingleList = new MySingleList();
       mySingleList.addFirst(1);
       mySingleList.addFirst(4);
       mySingleList.addFirst(3);
       mySingleList.addFirst(4);
       mySingleList.display();
       MySingleList.Node node = mySingleList.middleNode();
       mySingleList.show2(node);
       MySingleList.Node node1 = mySingleList.returnLastk(3);
       mySingleList.show2(node1);
       mySingleList.addLast(2);
       mySingleList.addindex(3,3);
       mySingleList.display();
       mySingleList.BubbleSort();
       mySingleList.display();
    }
}
