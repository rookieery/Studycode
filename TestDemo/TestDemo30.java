package TestDemo;

import java.util.*;

//空接口
public class TestDemo30 {
    private static void function1(List list) {
        if (list instanceof RandomAccess) {
            System.out.println("实现了RandomAccess接口，不使用迭代器");
            for (Object aList : list) {
                System.out.println(aList);
            }
            //for (int i = 0; i < list.size(); i++) {
            //                System.out.println(list.get(i));
            //            }
        } else {
            System.out.println("没实现RandomAccess接口，使用迭代器");
            for (Object aList : list) {
                System.out.println(aList);
            }
            //Iterator iterator = list.iterator();
            //            while (iterator.hasNext()) {
            //                System.out.println(iterator.next());
            //            }
        }
    }

    private static void function2(List list) {
        if (list instanceof RandomAccess) {
            System.out.println("实现了RandomAccess接口，使用迭代器");
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("没实现RandomAccess接口，不使用迭代器");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }

    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(String.valueOf(i));
        }
        long start1 = System.currentTimeMillis();
        function2(arrayList);//数组两种方式没区别？
        long end1 = System.currentTimeMillis();
        List<String> linkedList = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(String.valueOf(i));
        }
        long start2 = System.currentTimeMillis();
        function1(linkedList);//链表区别很大
        long end2 = System.currentTimeMillis();
        System.out.println("数组的遍历时间为：" + (end1 - start1));
        System.out.println("链表的遍历时间为：" + (end2 - start2));
    }
}
