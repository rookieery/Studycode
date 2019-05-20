import java.util.Scanner;

abstract class process {
    public final void browseGoods() {
        System.out.println("1、游览商品");
    }
    public final void selectGoods() {
        System.out.println("2、选择商品");
    }
    public abstract void callService();
    final void computerPrice() {
        System.out.println("4、电脑结算");
    }
    public abstract void orderPay();
   public abstract void sendService();
   public final void showOrder(){
        System.out.println("7、结算订货");
    }
    public boolean hook() {
   return false;
}
    final void start() {
        browseGoods();
        selectGoods();
        if (hook()) {
            callService();
        }
        computerPrice();
        orderPay();
        sendService();
        showOrder();
    }
}
class Jindong extends process {
    @Override
    public void sendService() {
        System.out.println("6、京东派送");
    }

    @Override
    public void orderPay() {
        System.out.println("5、微信");
    }

    @Override
    public void callService() {
        System.out.println("3、京东客服为您服务");
    }
    public String[]  getAnwser() {
        System.out.println("您是否需要客服为您服务？y/n");
        Scanner anwser = new Scanner(System.in);
        String str = anwser.nextLine();
        String[] a = new String [1];
        a[0] = str;
        /*Scanner anwser1 = new Scanner(System.in);
        String str1 = anwser1.nextLine();
        a[1] = str1;*/
        return a ;
    }
   public void  getAnwser1(int[] arr) {
        System.out.println("您是否需要客服为您服务？y/n");
        Scanner anwser = new Scanner(System.in);
       String str = anwser.nextLine();
        Scanner anwser1 = new Scanner(System.in);
        String str1 = anwser.nextLine();

    }
    public boolean hook() {
        //String s = null;
       // getAnwser1(s);
        int[] arr1 = new int[10];
        getAnwser1(arr1);

        String[] arr = getAnwser();
        /*String str = getAnwser();*/
        if (arr[0].equals("y")) {
            return true;
        }
        return false;
    }
}
public class Illusion {
    public static void main(String[] args) {
        process p = new Jindong();
        p.start();
    }
}
