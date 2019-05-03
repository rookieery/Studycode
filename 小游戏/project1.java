package 小游戏;

import java.util.Random;
import java.util.Scanner;

public class project1 {
    public void menu() {
        System.out.println("******************");
        System.out.println("**** 1.play  *****");
        System.out.println("**** 0.exit  *****");
        System.out.println("******************");
    }
    public void game() {
        Random random = new Random();
        int n = random.nextInt(99) + 1;
       while(true) {
           System.out.println("请输入你想猜的数字：");
           Scanner scanner = new Scanner(System.in);
           int input = scanner.nextInt();
           if(input > n) {
               System.out.println("猜大了！");
           }
           else if (input < n) {
               System.out.println("猜小了！");
           }
           else {
               System.out.println("恭喜你，猜对了！");
               break;
           }
       }
    }
    public void start(int input) {
        project1 q = new project1();
        while(true) {
            if (input == 1) {
                q.game();
                break;
            }
            else if (input == 0) {
                break;
            }
            else {
                System.out.println("选择错误，请重新输入！");
                Scanner scanner = new Scanner(System.in);
                int i = scanner.nextInt();
                start(i);
            }
        }
    }
    public static void main(String[] args) {
        project1 p = new project1();
        p.menu();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        p.start(input);
        System.out.println("游戏结束，欢迎下次再玩！");
    }
}
