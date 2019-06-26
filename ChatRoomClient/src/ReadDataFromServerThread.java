import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadDataFromServerThread extends Thread{
    private Socket client;

    public ReadDataFromServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream in = client.getInputStream();
            Scanner scanner = new Scanner(in);
            while (true) {
                try {
                    String message = scanner.nextLine();
                    System.out.println("来自服务端>" +
                            message);
                }catch (NoSuchElementException e) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
