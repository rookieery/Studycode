import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SingleThreadClient {
    public static void main(String[] args) {
        //1. 服务端地址，端口号
        String ip = "127.0.0.1";//本机IP地址
        int port = 1314;
        try {
            //2.Socket
            Socket client = new Socket(ip,port);
            //3.数据传输（键盘输入，输出到显示屏）
            //3.1 发送数据
            OutputStream out = client.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            printStream.println("服务器，我来了");
            printStream.flush();
            //3.2 接收数据
            InputStream in = client.getInputStream();
            Scanner scanner = new Scanner(in);
            System.out.println("来自服务端的数据："+scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
