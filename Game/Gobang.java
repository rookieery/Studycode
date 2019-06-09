package Game;

import java.util.Scanner;

public class Gobang {
    private int row = 15;
    private int col = 15;
    private int Long = 4;
    public void menu() {
        System.out.println("*********************");
        System.out.println("**** 1.one play *****");
        System.out.println("**** 2.two play *****");
        System.out.println("**** 0.exit     *****");
        System.out.println("*********************");
    }
    public void initboard(char[][] arr) {
        int i = 0;
        int j = 0;
        for (i = 0; i < row; i++)
        {
            for (j = 0; j < col; j++)
            {
                arr[i][j] = ' ';
            }
        }
    }
    public void printboard(char[][] arr) {
        int i ;
        int j ;
        int a ;
        for (a = 1; a <= row; a++)
        {
            System.out.print("   "+a);
        }
        System.out.print("\n");
        for (i = 0; i < row; i++)
        {
            for (j = 0; j < col; j++)
            {
                System.out.print(arr[i][j]);
                if (j <= col)
                {
                    System.out.print("|");
                }
            }
            System.out.print("\n");
            for (j = 0; j < col; j++)
            {
                if (i <= row)
                {
                    System.out.print("---");
                }
                if (j <= col)
                {
                    System.out.print("|");
                }
            }
            System.out.println(i+1);
            System.out.print("\n");
        }
    }
    public void palyer1move(char[][] arr,int[] t) {
        while (true) {
            System.out.println("玩家1走，请输入坐标");
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            Scanner scanner1 = new Scanner(System.in);
            int b = scanner1.nextInt();
            t[0] = a;
            t[1] = b;
            if (a > 0 && a <= row && b > 0 && b <= col)
            {
                if (arr[a - 1][b - 1] == ' ')
                {
                    arr[a - 1][b - 1] = '*';
                    break;
                }
			else
                {
                    System.out.println("该位置已有棋子，请重新输入！");
                }
            }
		else
            {
                System.out.println("非法坐标，请重新输入！");
            }
        }
    }
    public void palyer2move(char[][] arr,int[] t) {
        while (true) {
            System.out.println("玩家2走，请输入坐标");
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            Scanner scanner1 = new Scanner(System.in);
            int b = scanner1.nextInt();
            t[0] = a;
            t[1] = b;
            if (a > 0 && a <= row && b > 0 && b <= col)
            {
                if (arr[a - 1][b - 1] == ' ')
                {
                    arr[a - 1][b - 1] = '$';
                    break;
                }
                else
                {
                    System.out.println("该位置已有棋子，请重新输入！");
                }
            }
            else
            {
                System.out.println("非法坐标，请重新输入！");
            }
        }
    }
    public void computermove(int[] t) {

    }
    public int judge(char[][] arr,int[] t)
    {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count8 = 0;
        int n;
        int i;
        int j;
        i = t[0] - 1;
        j = t[1] - 1;
        for (n = 1; n <= Long; n++)//方向向上
        {
            if (arr[i + n][j] == arr[i][j])
            {
                count1++;
            }
            else
            {
                count1 = 0;
                break;
            }
        }
        for (n = 1; n <= Long; n++)//方向向下
        {
            if (arr[i - n][j] == arr[i][j])
            {
                count2++;
            }
            else
            {
                count2 = 0;
                break;
            }
        }
        for (n = 1; n <= Long; n++)//方向向左
        {
            if (arr[i][j - n] == arr[i][j])
            {
                count3++;
            }
            else
            {
                count3 = 0;
                break;
            }
        }
        for (n = 1; n <= Long; n++)//方向向右
        {
            if (arr[i][j + n] == arr[i][j])
            {
                count4++;
            }
            else
            {
                count4 = 0;
                break;
            }
        }
        for (n = 1; n <= Long; n++)//方向向左下
        {
            if (arr[i - n][j - n] == arr[i][j])
            {
                count5++;
            }
            else
            {
                count5 = 0;
                break;
            }
        }
        for (n = 1; n <= Long; n++)//方向向左上
        {
            if (arr[i - n][j + n] == arr[i][j])
            {
                count6++;
            }
            else
            {
                count6 = 0;
                break;
            }
        }
        for (n = 1; n <= Long; n++)//方向向右下
        {
            if (arr[i + n][j - n] == arr[i][j])
            {
                count7++;
            }
            else
            {
                count7 = 0;
                break;
            }
        }
        for (n = 1; n <= Long; n++)//方向向右上
        {
            if (arr[i + n][j + n] == arr[i][j])
            {
                count8++;
            }
            else
            {
                count8 = 0;
                break;
            }
        }
        if ((count1 + count2 == Long) || (count3 + count4 == Long) ||
                (count5 + count8 == Long) || (count6 + count7 == Long))
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
   public void game2()
    {
        char[][] arr = new char[row][col];
        int flag = 0;
        Gobang p = new Gobang();
        p.initboard(arr);
        p.printboard(arr);
        while (true) {
            int[] a = new int[2];
            p.palyer1move(arr,a);
            p.printboard(arr);
            flag = judge(arr,a);
            if (flag == 0) {
                break;
            }
            int[] b = new int[2];
            p.palyer2move(arr,b);
            p.printboard(arr);
            flag = judge(arr,b);
            if (flag == 0) {
                break;
            }
        }
    }
    public void start() {
        Gobang q = new Gobang();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        while (true) {
            if (input == 0) {
                break;
            }
            else if(input == 2) {
                q.game2();
                System.out.println("游戏结束,欢迎下次再玩！");
                break;
            }
            else {
                System.out.println("选择错误，请重新输入！");
                start();
            }
        }
    }
    public static void main(String[] args) {
        Gobang q = new Gobang();
        q.menu();
        q.start();
    }
}
