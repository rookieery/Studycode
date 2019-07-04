package Exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QuestionFour {
    public static void main(String[] args) {
        List<String > list = new ArrayList<>();
        list.add("hello");
        list.add("bit");
        list.add("hello");
        list.add("java");
        Iterator<String > iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.equals("bit")) {
                //集合的遍历时修改（不只是删除）会引发异常
                list.remove("bit");
            }
            System.out.println(str);
        }
    }
}
