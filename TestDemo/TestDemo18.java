package TestDemo;

import java.util.*;

public class TestDemo18 {
    //collections工具类
    private static void code1(){
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "Java", "C++", "PHP");
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
    }
    private static void code2() {
        List list = Collections.emptyList();
//        list.add("Java");// UnsupportedOperationException
        System.out.println(list.isEmpty());
    }
    private static void code3() {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            integers.add(random.nextInt(100));//[0,100]
        }
        System.out.println(integers);
        Integer maxValue = Collections.max(integers);// Integer Comparable
        System.out.println(maxValue);
    }
    private static void code4() {
        List<Integer> integers = new ArrayList<>();
        for (int i = 1; i <= 54; i++) {
            integers.add(i);
        }
        System.out.println(integers);
        Collections.shuffle(integers);
        System.out.println(integers);
        Collections.shuffle(integers);
        System.out.println(integers);
    }
    private static void code5() {
        Map<Integer,String> map = Collections.singletonMap(1,"666");
        System.out.println(map);
//        map.put(2,"777");////UnsupportedOperationException
    }
    public static void main(String[] args) {
        code5();
    }
}
