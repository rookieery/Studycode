import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {
    //储存所有注册的客户端
    private static final Map<String, Socket> socketMap = new ConcurrentHashMap<>();
    private final Socket client;
    private String name;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            //获取客户端输入流
            InputStream in = this.client.getInputStream();
            Scanner scanner = new Scanner(in);
            while (true) {
                String line = scanner.nextLine();
                if (line.startsWith("register:")) {
                    String[] segments = line.split(";");
                    if (segments.length == 2 && segments[0].equals("register")) {
                        String name = segments[1];
                        register(name);
                    }
                    continue;
                }
                if (line.startsWith("groupChat:")) {
                    String[] segments = line.split(":");
                    if (segments.length == 2 && segments[0].equals("groupChat")) {
                        String message = segments[1];
                        groupChat(message);
                    }
                    continue;
                }
                if (line.startsWith("privateChat:")) {
                    String[] segments = line.split(":");
                    if (segments.length == 3 && segments[0].equals("privateChat")) {
                        String name = segments[1];
                        String message = segments[2];
                        privateChat(name, message);
                    }
                    continue;
                }
                if (line.equalsIgnoreCase("bye")) {
                    quitChat();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void quitChat() {
        socketMap.remove(this.name);
        System.out.println(this.name + "下线了");
        printOnlineClient();
    }

    private void privateChat(String name, String message) {
        Socket socket = socketMap.get(name);
        if (socket != null) {
            sendMessage(socket, this.name + " 说:" + message);
        }
    }

    private void groupChat(String message) {
        for(Map.Entry<String,Socket> entry:socketMap.entrySet()) {//将Map集合变为Set集合???
            Socket socket = entry.getValue();
            if (socket == this.client) {
                continue;
            }
            sendMessage(socket, this.name + "说：" + message);
        }
    }

    private void register(String name) {
        this.name = name;
        socketMap.put(name,this.client);
        System.out.println(name + "注册到系统中");
        sendMessage(this.client, "欢迎，" + name + "注册成功");
        printOnlineClient();
    }
    //打印当前在线客户人数
    private void printOnlineClient() {
        System.out.println("当前在线的客户端有：" + socketMap.size() + "个，名称列表如下：");
        for(String name:socketMap.keySet()) {//keySet()取得所有的key信息
            System.out.println(name);
        }
    }
    //发指定消息到客户端
    private void sendMessage(Socket client, String s) {
        try {
            OutputStream out = client.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            printStream.println(s);
            printStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
