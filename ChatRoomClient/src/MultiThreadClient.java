import java.io.IOException;
import java.net.Socket;

public class MultiThreadClient {
    public static void main(String[] args) {
        String ip = "127.0.0.1";//本机IP地址
        int port = 1314;
        try {
            final Socket socket = new Socket(ip,port);
            new WriteDataToServerThread(socket).start();
            new ReadDataFromServerThread(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
