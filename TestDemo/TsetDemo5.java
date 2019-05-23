package TestDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

interface Fruit1 {

}

interface Message1 {

}
class Test1 implements Fruit1, Message1 {

    public Test1(Integer a, Integer b) {

    }

    public Test1(Integer a, String b) {

    }

    public Test1(Integer a) {

    }

    public Test1() {

    }
}
public class TsetDemo5 {
    //反射调用构造方法
    public static void part1() {
        Class clz = Test1.class;
        System.out.println("包名：" + clz.getPackage().getName());
        System.out.println("父类：" + clz.getSuperclass().getName());//类的全限定名 包名.类名
        System.out.println("父类：" + clz.getSuperclass().getSimpleName());//类名
        System.out.println("接口：");
        Class<?>[] interfaces = clz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(clz.getName());
            System.out.println(clz.getSimpleName());
        }
    }

    public static void main(String[] args) {
        part1();
        Class clz = Test1.class;
        Constructor[] constructors = clz.getConstructors();
        System.out.println("获取所有的构造方法");
        for (Constructor c : constructors) {
            Class[] parameterCls = c.getParameterTypes();
            String parameter = Arrays.toString(parameterCls);
            System.out.println(
                    c.getName() +
                            "(" + parameter + ")"
            );
            System.out.println("获取指定的构造方法");
            try {
                Constructor constructor = clz.getConstructor(Integer.class, Integer.class);
                System.out.println(constructor);
                try {
                    Object object = constructor.newInstance(20,35);
                    System.out.println(object.getClass());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
}