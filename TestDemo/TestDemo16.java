package TestDemo;

import java.util.*;

//迭代器和map
public class TestDemo16 {
    private static void code1() {
        Set<String> list = new HashSet<>();
        list.add("Java");
        list.add("C++");
        list.add("PHP");
        System.out.println(list);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if ("Java".equals(str)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    private static void code2() {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("C++");
        list.add("PHP");
        ListIterator<String> listIterator = list.listIterator();
        System.out.println("从前往后：");
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next());
            System.out.print(" -> ");
        }
        System.out.println();
        System.out.println("从后往前：");
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous());
            System.out.print(" <- ");
        }
        System.out.println();
    }

    private static void code3() {
        Map<Integer, String> map = new HashMap<>();//不能实现比较器？
        map.put(1, "Java");
        map.put(2, "C");
        map.put(4, "Python");
        map.put(3, "C++");
        map.put(5, null);
        map.put(6, "Python");
        map.put(1, "PHP");
        map.put(null, "SQL");//8
        System.out.println(map);
//        System.out.println("集合元素的个数：" + map.size());
//        System.out.println("集合元素的所有key：" + map.keySet());
//        System.out.println("集合元素的所有value：" + map.values());
//        System.out.println("集合是否为空：" + map.isEmpty());
//        System.out.println("获取key=null的value：" + map.get(null));
//        System.out.println("获取key=4的value：" + map.get(4));
//        System.out.println("获取key=9的value：" + map.get(9));
//        System.out.println("集合是否包含key=12：" + map.containsKey(12));
        for (int i = 0; i < map.size(); i++) {//不能用for？用迭代器改变成的foreach
            System.out.print(map.get(i) + " ");
        }
        System.out.println();
        //遍历map
        //1. keySet 2. key -> value
        for (Integer key : map.keySet()) {
            System.out.print(key + " = " + map.get(key) + " ");
        }
        System.out.println();
        Set<Integer> set1 = map.keySet();
        Iterator<Integer> iterator = set1.iterator();
        while (iterator.hasNext()) {//和上面实质一样
            Integer a = iterator.next();
            System.out.print(a + " = " + map.get(a) + " ");
        }
        System.out.println();
        //2. value
        for (String value : map.values()) {
            System.out.print(value + " ");
        }
        System.out.println();
        //Set<String> set2 = map.keySet();//这又是什么情况？keySet()是整形

        //3. entrySet
        Set<Map.Entry<Integer, String>> set = map.entrySet();
        for (Map.Entry<Integer, String> entry : set) {
            System.out.print(entry.getKey() + " = " + entry.getValue() + " ");
        }
    }

    private static void code4() {
        Date date = new Date();
        Person2 person2 = new Person2();
        person2.setAge(22);
        person2.setBirthday(date);
        person2.setName("zhangsan");
        person2.setSkills(new String[]{"Java", "C++"});
        Map<Person2,String> map = new HashMap<>();
        map.put(person2,person2.toString());
        Person2 person3 = new Person2();
        person3.setAge(22);
        person3.setBirthday(date);
        person3.setName("zhangsan");
        person3.setSkills(new String[]{"Java", "C++"});
        //e.hash==hash && ((e.key==key) || e.key.equals(key))
        System.out.println(map.get(person3));
        person3.setAge(21);
        System.out.println(map.get(person3));
    }
    private static void code5() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);
        System.out.println(list);
        list.sort(Integer::compare);
        System.out.println(list);
    }
    public static void main(String[] args) {
        code5();
    }
}
