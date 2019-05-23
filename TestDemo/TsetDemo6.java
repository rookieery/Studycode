package TestDemo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

public class TsetDemo6 {
    //反射调用普通方法和属性
    public static void part1() {
        Class cls = Person.class;
        System.out.println("获取所有的方法：");
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("获取指定方法");
        Person person = null;
        try {
            person = (Person) cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Person before :" + person);
        //2.获取对象的方法，并且调用 （修改）
        //访问修饰符  返回类型  类名.方法名([参数类型列表]) 异常类型列表
        Method method = null;
        try {
            method = cls.getMethod("setName", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object returnValue = null;
        try {
            returnValue = method.invoke(person, "Tom");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(returnValue);//null
        System.out.println("Person after :" + person);
        //3.获取对象的方法，并且调用 （取值）
        Method getNameMethod = null;
        try {
            getNameMethod = cls.getMethod("getName");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object returnName = null;
        try {
            returnName = getNameMethod.invoke(person);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(returnName);
    }

    public static void main(String[] args) {
        part1();
        Class cls = Student.class;
        System.out.println("获取所有属性");
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            System.out.println(field.getType() + " " + field.getName());
            System.out.println(field);
        }
        System.out.println("获取指定属性");
        Field skillField = null;
        try {
            skillField = cls.getDeclaredField("skill");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println(skillField);
        //使用属性
        Student student = new Student();
        student.setSkill("Java,PHP,Python,JavaScript");
        System.out.println(student);
//            student.getSkill()
        //true表示可以访问私有的属性
        skillField.setAccessible(true);
        //get 获取
//            Object skillValue = skillField.get(student);
//            System.out.println(skillValue);
        //set 修改
        try {
            skillField.set(student, "C++");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(student);

    }
}
class Person {

    private int age;

    public String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class Student extends Person {

    private String school;
    private String skill;
    private LocalDateTime birthday;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "Student{" +
                "school='" + school + '\'' +
                ", skill='" + skill + '\'' +
                ", birthday=" + birthday +
                "} " + super.toString();
    }
}
