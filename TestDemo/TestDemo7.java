package TestDemo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestDemo7 {
    public static void  code1() {
        File file = new File("G:" + File.separator + "mgtv" + File.separator +
                "javacode" + File.separator + "javase522" + File.separator + "Hello.java");
        System.out.println("文件名：" + file.getName());
        System.out.println("是否是文件：" + file.isFile());
        System.out.println("是否是目录：" + file.isDirectory());
        System.out.println("是否是隐藏文件：" + file.isHidden());
        System.out.println("文件大小(字节)：" + file.length());
        //1970-01-01 00:00:00 -> now ms
        System.out.println("最后修改时间：" + new Date(file.lastModified()));
        System.out.println("是否能够执行：" + file.canExecute());//.exe .msi
    }
    public static void code2() throws IOException {
        File file = new File("G:" + File.separator + "mgtv" + File.separator +
                "javacode" + File.separator + "javase522" + File.separator +
                "Hello.java");
        if (file.exists()) {
            System.out.println("Hello.java存在，不需要创建");
        } else {
            File parentfile = file.getParentFile();
            if (!parentfile.exists()) {
                boolean effect = parentfile.mkdirs(); //mkdir -p
                if (effect) {
                    System.out.println(parentfile.getAbsolutePath() + " 父目录不存在，创建成功");
                } else {
                    System.out.println(parentfile.getAbsolutePath() + " 父目录不存在，创建失败");
                }
            }
            if (parentfile.exists()) {
                System.out.println(parentfile.getAbsolutePath() + " 父目录存在");
                boolean effect = file.createNewFile();
                if (effect) {

                    System.out.println(file.getAbsolutePath() + " 创建成功");
                } else {
                    System.out.println(file.getAbsolutePath() + " 创建失败");
                }
            }
        }
    }
    public static void main(String[] args) {
        File file = new File("G:" + File.separator + "code" + File.separator +
                "untitled");
        int level = 0;
        printDirectory(level,file);
    }
    public static void printDirectory(int level, File file) {
        if (file.isFile()) {
            System.out.println(line(level)+ file.getName());
        }
        else {
            System.out.println(line(level)+ file.getName());
            File[] files = file.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    printDirectory(level + 2,files[i]);
                }
            }
        }
    }
    private static String space(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < level; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
    private static String line(int level) {
        StringBuilder sp = new StringBuilder();
        int i = 0;
        int count = 2;
        int t = 1;
        while (i < level) {
            if (i >= level - 1) {
                sp.append("_");
                i++;
                continue;
            }
            if (count == 2) {
                sp.append("|");
                count = 0;
                i++;
            }
            else {
                if (t == 1) {
                    sp.append("  ");
                    t = 0;
                }
                else {
                    sp.append(" ");
                }
                count++;
                i++;
            }
        }

        return sp.toString();
    }
}
