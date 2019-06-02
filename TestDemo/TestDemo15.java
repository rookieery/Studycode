package TestDemo;

import java.io.Serializable;
import java.util.*;

class Person3 implements Serializable, Comparable<Person3> {

    private String name;
    private transient Integer age;
    private String[] skills;
    private Date birthday;

    public Person3() {

    }

    public Person3(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + Arrays.toString(skills) +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person3 person3 = (Person3) o;
        return Objects.equals(name, person3.name) &&
                Objects.equals(age, person3.age) &&
                Arrays.equals(skills, person3.skills) &&
                Objects.equals(birthday, person3.birthday);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(name, age, birthday);
        result = 31 * result + Arrays.hashCode(skills);
        return result;
    }

    @Override
    public int compareTo(Person3 o) {
        int a = this.age;
        int b = o.getAge();
        return -1*Integer.compare(a, b);
    }
}

public class TestDemo15 {
        public static void main (String[]args){
//        Set<String> set  =new TreeSet<>();
//        set.add("Java");
//        set.add("C++");
//        set.add("PHP");
//        set.add("SQL");
//        set.add("JavaScript");
//        set.add("C");
//        set.add("Python");
//
//        System.out.println(set);


//        Set<Integer> set = new TreeSet<>();
//        set.add(50);
//        set.add(25);
//        set.add(30);
//        set.add(18);
            //已知升序

            //1. JDK中定义的类的实例化对象在TreeSet  如何改变
            //2. TreeSet中使用了自定义类型，排序是要求
            // a. 实现Comparable接口
            // b. 在TreeSet实例化的时候指定Comparator
            //Set<Person3> set = new TreeSet<>(Comparator.comparingInt(o -> o.getName().length()));
//            Set<Person3> set = new TreeSet<>(new Comparator<Person3>() {
//                @Override
//                public int compare(Person3 o1, Person3 o2) {
//                    return Integer.compare(o1.getName().length(),o2.getName().length());
//                }
//            });
            Set<Person3> set = new TreeSet<>();
            set.add(new Person3("Jack", 22));
            set.add(new Person3("Tom", 21));
            set.add(new Person3("Alice", 24));
            set.add(new Person3("张三", 20));
            System.out.println(set);
        }
    }
