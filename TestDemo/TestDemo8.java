package TestDemo;

import java.io.*;

public class TestDemo8 {
    public static void copy(String srcFilePath,String destFilePath) {
        if (srcFilePath == null || destFilePath == null) {
            throw new IllegalArgumentException();//非法参数异常
        }
        File srcfile = new File(srcFilePath);
        File destdile = new File(destFilePath);
        if (!srcfile.isFile() || !srcfile.exists()){
            throw new IllegalArgumentException();
        }
        if (!destdile.getParentFile().exists()) {
            destdile.getParentFile().mkdirs();
        }
//        try(Reader reader = new FileReader(srcfile);
//        Writer writer = new FileWriter(destdile)) {
//            char[] buff = new char[1024];
//            int len = -1;
//            while ((len = reader.read(buff)) != -1) {
//                writer.write(buff,0,len);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try(InputStream inputStream = new FileInputStream(srcfile);
            OutputStream outputStream = new FileOutputStream(destdile,true)){
            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buff)) != -1) {
                outputStream.write(buff,0,len);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void merge(String[] scrFiles,String destFilePath) {
        if (scrFiles == null || destFilePath == null) {
            throw new IllegalArgumentException();
        }
        File[] scrfiles = new File[scrFiles.length];
        File destfile = new File(destFilePath);
        for (int i = 0; i < scrfiles.length ; i++) {
            scrfiles[i] = new File(scrFiles[i]);
            if (scrfiles[i].isFile()) {
                try(InputStream inputStream = new FileInputStream(scrfiles[i]);
                    OutputStream outputStream = new FileOutputStream(destfile)){
                    byte[] buff = new byte[1024];
                    int len = -1;
                    while ((len = inputStream.read(buff)) != -1) {
                        outputStream.write(buff,0,len);
                    }
                    outputStream.flush();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void depart(String srcFilePath,String[] destFiles) {
        if (srcFilePath == null || destFiles == null) {
            throw new IllegalArgumentException();
        }
        File scrfile = new File(srcFilePath);
        File[] destfiles = new File[destFiles.length];
        try(InputStream inputStream = new FileInputStream(srcFilePath)) {
            byte[] buff = new byte[5];
            int len = -1;
            while ((len = inputStream.read(buff)) != -1) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String srcFilePath = "G:\\mgtv\\architecture-buildings-burj-khalifa-1688504.jpg";
        String[] scrFiles = {"G:\\mgtv\\adorable-animal-canine-662417.jpg"};
        String destFilePath = "G:\\mgtv\\image\\selected.gif";
        //copy(srcFilePath,destFilePath);
        merge(scrFiles,destFilePath);
    }
}
