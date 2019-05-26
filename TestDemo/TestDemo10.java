package TestDemo;

import java.io.*;
import java.util.Arrays;

public class TestDemo10 {
    //内存操作流
    private static void code1() {
        String s = "hello java";
        byte[] buff = s.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buff);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = -1;
        while ((len = in.read()) != -1) {//此read无参数
            len = Character.toUpperCase(len);
            out.write(len);
        }
        byte[] newarr = out.toByteArray();
        System.out.println(newarr);
        System.out.println(new String(newarr));
        System.out.println(Arrays.toString(newarr));
        System.out.println(Arrays.toString(new String(newarr).toCharArray()));
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //打印流
    private static void code2() {
        File file = new File("G:" + File.separator + "mgtv" + File.separator + "c" + File.separator + "print.java");
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        try (PrintStream printStream = new PrintStream(new FileOutputStream(file)) {
        }) {
            printStream.printf("姓名：%s  年龄：%d  身高：%.2f\n", "张三", 28, 176.456);
            printStream.printf("姓名：%s  年龄：%d  身高：%.2f", "张三", 28, 176.456);
            printStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
