package Exercises;
//值传递
public class QuestionOne {
    private String str = "6";
    private Integer age = 10;

    public static void main(String[] args) {
        // Gc Roots  虚拟机栈和本地方法栈的局部变量，类中的常量和静态变量
        QuestionOne qo = new QuestionOne();
        qo.change(qo.str);
        //qo.changeTest(qo);
        qo.change1(qo.age);
        System.out.println(qo.str);
        System.out.println(qo.age);
    }

    private void change(String str) {//此处的修改对原引用不可见
        str += "10";
    }

    private void change1(Integer age) {//这也是开新空间？？
        age += 20;
    }
    private void changeTest(QuestionOne qo) {//普通的引用
        qo.age = 20;
    }
}
