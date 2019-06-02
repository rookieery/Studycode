package TestDemo;

import java.io.Serializable;
import java.util.*;
//重写hashCode()与重写equals来改变判等方法
class Person2 implements Serializable {
    private String name;
    private transient Integer age;
    private String[] skills;
    private Date birthday;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return Objects.equals(name, person2.name) &&
                Objects.equals(age, person2.age) &&
                Arrays.equals(skills, person2.skills) &&
                Objects.equals(birthday, person2.birthday);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(name, age, birthday);
        result = 31 * result + Arrays.hashCode(skills);
        return result;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + Arrays.toString(skills) +
                ", birthday=" + birthday +
                '}';
    }
}

public class TestDemo14 {
    public static void main(String[] args) {
        Person2 person1 = new Person2();
        person1.setName("Jack");
        Person2 person2 = new Person2();
        person2.setName("Tom");
        List<Person2> person2s = new ArrayList<>();
        person2s.add(person1);
        person2s.add(person2);
        System.out.println(person2s);
        Person2 person3 = new Person2();
        person3.setName("Tom");
        System.out.println("是否包含Person3:"+person2s.contains(person3));
        System.out.println("是否包含Person2:"+person2s.contains(person2));
        System.out.println(person2.equals(person3));
        System.out.println(person2s);
    }
}
