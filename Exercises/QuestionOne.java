package Exercises;

//值传递
public class QuestionOne {
    private String str = "6";
    private Integer age = 10;
    int a = 10;
    int b = 20;
    StringBuffer A = new StringBuffer("A");
    StringBuffer B = new StringBuffer("B");

    //成员变量可以通过传参类的实例化对象来改变其成员属性？？？
    //局部变量，基本类型无法改变其属性，引用类型除非调用某些方法，否则也无法改变其属性？？？
    //局部变量基本类型无法改变其属性：c语言的指针可以做到（暴露地址），从而也不安全
    public static void main(String[] args) {
        // Gc Roots  虚拟机栈和本地方法栈的局部变量，类中的常量和静态变量
        QuestionOne qo = new QuestionOne();
        qo.change(qo.str);
        //qo.changeTest(qo);
        qo.change1(qo.age);
        System.out.println(qo.str);
        System.out.println(qo.age);
        swap(qo);
        System.out.println(qo.a);
        System.out.println(qo.b);
    }

    private static void swap(QuestionOne qo) {
        int tmp = qo.a;
        qo.a = qo.b;
        qo.b = tmp;
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
