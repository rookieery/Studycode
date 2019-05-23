package TestDemo;

import Game.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class TestDemo2 {
    public static void main1(String[] args) {
        //三种获得Class对象的方法
        Date date = new Date();
        Class aclass = date.getClass();
        Class classz = Date.class;
        try {
            Class classzz = Class.forName("java.util.Date");
            System.out.println(classzz);
            System.out.println(aclass == classz);
            System.out.println(aclass == classzz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //用反射，通过Class对象的newInstance()方法来取得类的实例化对象
        Class dateClass = Date.class;
        try {
            Constructor constructor = dateClass.getConstructor(long.class);
            try {
                Object object = constructor.newInstance(30);
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
        Date date = new Date();
        try {
            Object object = dateClass.newInstance();//object在编译时是Object类型，（实际上）运行时是Date类型
            //Date object = (Date) dateClass.newInstance();
            System.out.println(object);
            System.out.println(object instanceof Date);
            System.out.println(object.getClass());
            System.out.println(date.equals(object));//不是同一个对象，但是是同一个类的对象???

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Date d =new Date();
        Object a = d;
        System.out.println(a instanceof Date);
        Object t = new Object();
        Object m = "Hello";
       // String s = (String) t;//1.ok  2.CCE
        //String e = (String) m;//1.ok  2.CCE
        //Person c = (Person) a;//1.ok  2.CCE



    }
}
