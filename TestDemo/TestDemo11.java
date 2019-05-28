package TestDemo;
//枚举
interface IColor {
    public String getColor();
}
enum Color implements IColor {
    RED("红色"),GREEN("绿色"),BLUE("蓝色");//这些都是枚举类的对象
    private String title;
    private Color(String title) {//必须提供一个带String类型参数的构造方法
        this.title = title;
    }
//    @Override
//    public String toString() {
//        return this.title;
//    }
    @Override
    public String getColor() {
        return this.title;
    }
}

public class TestDemo11 {
    public static void main(String[] args) {
        IColor iColor = Color.BLUE;
        System.out.println(iColor.getColor());
    }
}
