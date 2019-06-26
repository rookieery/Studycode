import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
    public static void main(String[] args) {
        int port = 1314;
        //线程池，这都是啥玩意！！！
        final ExecutorService executorService = Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动，运行在：" + serverSocket.getLocalSocketAddress());
            while (true) {
                final Socket socket = serverSocket.accept();
                System.out.println("客户端连接，来自：" + socket.getRemoteSocketAddress());
                executorService.execute(new ClientHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
