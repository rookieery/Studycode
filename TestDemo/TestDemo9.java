package TestDemo;

import java.io.*;

public class TestDemo9 {
    //字节输出流
    private static void code1() {
        File file = new File("G:" + File.separator + "mgtv" + File.separator + "a" + File.separator + "Hello.text");
        //判断目录是否存在
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        try (OutputStream outputStream = new FileOutputStream(file,true)) {
            outputStream.write(65);
            String str = "I老虎U!！";
            byte[] buff = str.getBytes();
            outputStream.write('\n');
            outputStream.write(buff);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //字节输入流
    private static void code2() {
        File file = new File("G:" + File.separator + "mgtv" + File.separator + "a" + File.separator + "Hello.text");
        //读文件，文件必须存在
        if (file.exists()) {
            try (InputStream inputStream = new FileInputStream(file)) {
                byte[] buff = new byte[5];
                int len = -1;
                while ((len = inputStream.read(buff)) != -1) {
                    System.out.println(new String(buff, 0, len));//字节输出
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //字符输出流
    private static void code3() {
        File file = new File("G:" + File.separator + "mgtv" + File.separator + "b" + File.separator + "Hello.java");
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        try (Writer writer = new FileWriter(file)) {
            writer.append('A');
            writer.append('1');
            writer.append('\n');
            writer.write("I老虎U!！");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //字符输入流
    private static void code4() {//结果？？？
        File file = new File("G:" + File.separator + "mgtv" + File.separator + "b" + File.separator + "Hello.java");
        if (file.exists()) {
            try (Reader reader = new FileReader(file)){
                char[] buff = new char[4];
                int len = -1;
                while ((len = reader.read(buff)) != -1) {
                    System.out.println(new String(buff,0,len));//字符输出
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //转换流
    private static void code5() {
        File file = new File("G:" + File.separator + "mgtv" + File.separator + "a" + File.separator + "Hello.text");
        try(InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream)) {
            char[] buff = new char[6];
            int len = -1;
            while ((len = reader.read(buff)) != -1) {
                System.out.println(new String(buff,0,len));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
