package Exercises;

import java.io.IOException;
//编译时异常和运行时异常
public class QuestionSeven {
    public static void dosomething() throws IOException {
        System.out.println();
    }
    public static void dosomething1() throws NullPointerException{
        System.out.println();
    }
    public static void main(String[] args) {
        dosomething1();
        //此处必须得显示捕获
        try {
            dosomething();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
