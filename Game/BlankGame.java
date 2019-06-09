package Game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Goods {
    private String name;
    private double price;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                " name='" + name + '\'' +
                        ", price=" + price;
    }
}

class Order {
    private GoodsCenter goodsCenter = GoodsCenter.getInstance();
    private Map<Integer, Integer> orderMap = new HashMap<>();
    private Set<Integer> orderSet = orderMap.keySet();

    void add() {
        System.out.println("请输入下单信息[编号 数量]（如下格式：1  2 ）:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int mount = scanner.nextInt();
        if (!goodsCenter.goodsMap.containsKey(number)) {
            System.out.println("输入编号有误，请选择已上架的商品编号");
        } else {
            orderMap.put(number, mount);
            show();
        }
    }

    void delete() {
        System.out.println("请输入下单信息[编号 数量]（如下格式：1  2 ）:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int mount = scanner.nextInt();
        if (!orderMap.containsKey(number)) {
            System.out.println("输入编号有误，请选择已上架的商品编号");
        } else {
            if (mount >= orderMap.get(number)) {
                orderMap.remove(number);
            } else {
                orderMap.put(number, orderMap.get(number) - mount);
            }
            show();
        }
    }

    private double compute() {
        double amount = 0;
        for (Integer tmp : orderSet) {
            amount += orderMap.get(tmp) * goodsCenter.goodsMap.get(tmp).getPrice();
        }
        return amount;
    }

    void show() {
        System.out.println("******************* 订单发票 ********************");
        System.out.println("打印时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("=================================================");
        System.out.println("编号          名称             数量          单价");
        Set<Integer> set = orderMap.keySet();
        for (Integer number : set) {
            int mount = orderMap.get(number);
            String name = goodsCenter.goodsMap.get(number).getName();
            double price = goodsCenter.goodsMap.get(number).getPrice();
            System.out.println(number + "            " + name + "              " + mount + "           " + price);
        }
        System.out.println("=================================================");
        System.out.println("总价：" + compute());
        System.out.println("*************************************************");
    }
}

class GoodsCenter {
    Map<Integer, Goods> goodsMap = new HashMap<>();
    private final static GoodsCenter instance = new GoodsCenter();

    private GoodsCenter() {

    }

    public static GoodsCenter getInstance() {
        return instance;
    }

    void add() {
        System.out.println("请输入上架商品信息（如下格式：1 餐巾纸 1.4）:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String name = scanner.next();
        double price = scanner.nextDouble();
        goodsMap.put(number, new Goods(name, price));
        show();
    }

    void amend() {
        System.out.println("请输入修改商品信息（如下格式：1 餐巾纸 1.4 ）:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        String name = scanner.next();
        double price = scanner.nextDouble();
        if (!goodsMap.containsKey(number)) {
            System.out.println("请选择上架的商品编号，当前修改商品未设置");
        } else {
            goodsMap.put(number, new Goods(name, price));
            show();
        }
    }

    void delete() {
        System.out.println("请输入下架商品信息编号（如下格式：1 ）:");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if (!goodsMap.containsKey(number)) {
            System.out.println("请选择上架的商品编号，当前下架商品未设置");
        } else {
            goodsMap.remove(number);
        }
        show();
    }

    void show() {
        System.out.println("***********************货品清单************************");
        System.out.println("           编号        产品名称          单价          ");
        Set<Integer> set = goodsMap.keySet();
        for (Integer number : set) {
            String str = goodsMap.get(number).getName();
            double price = goodsMap.get(number).getPrice();
            System.out.println("           " + number + "           " + str + "             " + price + "          ");
        }
        System.out.println("*******************************************************");
    }
}

class CheckStand {
    private GoodsCenter goodsCenter = GoodsCenter.getInstance();
    private Order order = new Order();

    void menu() {
        System.out.println("***********************买单功能************************");
        System.out.println("        [S]查看 [A]下单 [D]取消 [L]游览 [R]返回        ");
        System.out.println("           输入： S A D L R进入操作                    ");
        System.out.println("*******************************************************");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        use(input);
    }

    void menus() {
        System.out.println("***********************设置功能************************");
        System.out.println("        [S]查看 [A]上架 [D]下架 [U]修改 [R]返回        ");
        System.out.println("           输入： S A D U R进入操作                    ");
        System.out.println("*******************************************************");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        set(input);
    }

    private void set(String input) {
        switch (input) {
            case "S": {
                goodsCenter.show();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                set(str);
                break;
            }
            case "A": {
                goodsCenter.add();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                set(str);
                break;
            }
            case "D": {
                goodsCenter.delete();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                set(str);
                break;
            }
            case "U": {
                goodsCenter.amend();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                set(str);
                break;
            }
            case "R": {
                BlankGame.menu();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                BlankGame.start(str);
                break;
            }
            default:
                System.out.println("非法输入，请重新输入！");
                menus();
                break;
        }
    }

    private void use(String input) {
        switch (input) {
            case "S": {
                order.show();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                use(str);
                break;
            }
            case "A": {
                order.add();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                use(str);
                break;
            }
            case "D": {
                order.delete();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                use(str);
                break;
            }
            case "L": {
                goodsCenter.show();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                use(str);
                break;
            }
            case "R": {
                BlankGame.menu();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                BlankGame.start(str);
                break;
            }
            default:
                System.out.println("非法输入，请重新输入！");
                menu();
                break;
        }
    }
}

public class BlankGame {
    static void menu() {
        System.out.println("*******************欢迎使用简易收银台******************");
        System.out.println("           [U] 使用 [S] 设置 [A]关于 [Q] 退出          ");
        System.out.println("           输入： U S A Q 进入操作                     ");
        System.out.println("*******************************************************");
    }

    private static void end() {
        System.out.println("*******************************************************");
        System.out.println("                   欢迎使用，下次再见                  ");
        System.out.println("*******************************************************");
    }

    private static void about() {
        System.out.println("**************************关于*************************");
        System.out.println("              名称：简易收银台                         ");
        System.out.println("              功能：基于字符界面的收银台操作系统       ");
        System.out.println("              作者：rookie                             ");
        System.out.println("              版本：v0.0.1                             ");
        System.out.println("              意见反馈：1814203288@qq.com              ");
        System.out.println("*******************************************************");
    }

    static void start(String input) {
        switch (input) {
            case "U": {
                CheckStand checkStand = new CheckStand();
                checkStand.menu();
                break;
            }
            case "S": {
                CheckStand checkStand = new CheckStand();
                checkStand.menus();
                break;
            }
            case "A": {
                about();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                start(str);
                break;
            }
            case "Q":
                end();
                break;
            default: {
                System.out.println("非法输入，请重新输入！");
                menu();
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                start(str);
                break;
            }
        }
    }

    public static void main(String[] args) {
        menu();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        start(input);
    }
}
