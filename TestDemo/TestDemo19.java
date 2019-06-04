package TestDemo;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
//Stream流的实例操作
class Order {
    private String title;
    private double price;
    private int amount;

    public Order(String title, double price, int amount) {
        this.title = title;
        this.price = price;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
public class TestDemo19 {
    public static void main(String[] args) {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("Iphone", 8999.99, 10));
        orderList.add(new Order("外星人笔记本", 12999.99, 5));
        orderList.add(new Order("MacBookPro", 18999.99, 5));
        orderList.add(new Order("Java从入门到放弃.txt", 9.99, 20000));
        orderList.add(new Order("中性笔", 1.99, 200000));
//        double totalPrice = 0.0D;
//        for (Order order : orderList){
//            totalPrice+= order.getPrice()*order.getAmount();
//        }
//        System.out.println("总金额："+totalPrice);
//        double allPrice = orderList.stream().map((order -> order.getPrice() * order.getAmount()))
//                .reduce((sum,x) -> sum + x).get();
//        System.out.println("总金额："+allPrice);
        DoubleSummaryStatistics statistics = orderList.stream().mapToDouble((Order::getAmount)).summaryStatistics();
        System.out.println("总订单数：" + statistics.getCount());
        System.out.println("总金额：" + statistics.getSum());
        System.out.println("最大金额：" + statistics.getMax());
        System.out.println("最小金额：" + statistics.getMin());
        System.out.println("平均金额：" + statistics.getAverage());
    }
}
