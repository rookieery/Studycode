package Exercises;

class A {

}

public class QuestionSix extends A{
    public void test() {
        System.out.println(super.getClass().getName());
    }

    public void test1() {
        System.out.println(this.getClass().getSuperclass().getName());
    }
    public static void main(String[] args) {
        new QuestionSix().test();
        new QuestionSix().test1();
    }
}
