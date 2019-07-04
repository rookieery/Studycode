package Exercises;

import java.util.ArrayList;
import java.util.List;
//异常体系
public class QuestionTwo {
    public static void main(String[] args) {
        System.out.println(testA());
        System.out.println(testB());
    }

    //注释也会进入编译？？？？
    //注：在此处把字符串也默认为是基本类型
    private static int testA() {
        int a = 0;
        try {
            a = 1;//1
            //这里会有一个暂存临时变量
            return a;//2
        } catch (Exception e) {
            a = 2;
            return a;
        } finally {
            a = 3;//3
        }
    }

    private static List<Integer> testB() {
        List<Integer> list = new ArrayList<>();
        try {
            list.add(1);
            // ArrayList list1 = list
            // return list1;
            return list;
        } catch (Exception e) {
            list.set(0,2);
            return list;
        } finally {
            list.set(0,3);
        }
    }
}
