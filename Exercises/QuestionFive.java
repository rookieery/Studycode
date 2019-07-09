package Exercises;

class Father {
    public static void code1() {
        System.out.println("I am a father");
    }

    public void code2() {
        System.out.println("I am a father");
    }
}

class Son extends Father {
    public static void code1() {
        System.out.println("I am a son");
    }
    public void code2() {
        System.out.println("I am a son");
    }
}

public class QuestionFive {
    public static void main(String[] args) {
        Father father = new Son();
        father.code2();
        father.code1();
    }
}
