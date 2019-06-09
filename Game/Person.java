package Game;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GAOBO
 * Date: 2019-04-06
 * Time: 14:45
 */
public class Person {
    private String name;
    private int score;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        this.score++;
    }

    //出拳
    public String fist() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入出的招数：石头，剪刀，布");
        String str = scanner.nextLine();
        return str;
    }
}
