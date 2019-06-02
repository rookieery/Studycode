package TestDemo;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
//序列化与反序列化
class Person1 implements Serializable {

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
    public String toString() {
        return "Person1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + Arrays.toString(skills) +
                ", birthday=" + birthday +
                '}';
    }
}

public class TestDemo13 {
    private static void code1() {
        Person1 person1 = new Person1();
        person1.setAge(28);
        person1.setName("Tom");
        person1.setBirthday(new Date());
        person1.setSkills(new String[]{"Java", "C++", "Shell"});
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(
                "G:" + File.separator + "mgtv" + File.separator + "e" + File.separator + "Person.data"))) {
            objectOutputStream.writeObject(person1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void code2() {
        File file = new File("G:" + File.separator + "mgtv" + File.separator + "e" + File.separator + "Person.data");
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
            Object object = objectInputStream.readObject();
            System.out.println(object);
            Person1 person1 = (Person1)object;
            System.out.println(person1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        code2();
    }
}
