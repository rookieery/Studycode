import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SingleThreadServer {
    public static void main(String[] args) {
        //1. 准备地址和端口
        int port  = 1314;
        try {
            //2. ServerSocket
            ServerSocket serverSocket = new ServerSocket(port);
            //3. 等待客户端连接
            Socket client = serverSocket.accept();
            //4. 数据接收和发送
            //4.1 接收
            InputStream in = client.getInputStream();
            Scanner scanner = new Scanner(in);
            System.out.println("客户端说："+scanner.nextLine());
            //4.2 发送
            OutputStream out = client.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            printStream.println("欢迎，欢迎");
            printStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
