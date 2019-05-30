package TestDemo;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.in;

public class TestDemo12 {
    //两种输入流
    private static void code1() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in))){
            System.out.println("从键盘读取一行数据");
            String line = bufferedReader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void code2() {
        try (BufferedReader bufferedReader = new BufferedReader
                (new FileReader("G:"+File.separator+"mgtv"+File.separator+"c"+File.separator+"print.java"))){
            System.out.println("从键盘读取一行数据");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void code3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("从键盘读取数据");
        if (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
        System.out.println("请输入年龄：");
        if (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }else {
            System.out.println("输入的内容不是整数");
        }
        System.out.println("请输入生日：");
        //2019-05-25
        //4-2-2
        //\d -> 0-9
        //{4} -> 出现的次数
        //\d{4}-\d{2}-\d{2}
        //[1-9][0-9]{4,}
        if (scanner.hasNext("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println(scanner.next());
        } else {
            System.out.println("生日格式不合法");
        }
        scanner.close();
    }
    private static void code4() {
        try (Scanner scanner = new Scanner(new FileReader
                ("G:"+File.separator+"mgtv"+File.separator+"c"+File.separator+"print.java"))){
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
