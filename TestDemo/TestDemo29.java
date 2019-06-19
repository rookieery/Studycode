package TestDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理模式
interface Image {
    void show();
}

class RealImage implements Image {

    public RealImage() {
        try {
            //程序暂停2s模拟系统开销
            Thread.sleep(2000);
            System.out.println("图片正在装载中...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        System.out.println("成功绘制实际的图片");
    }
}

class ProxyImage implements Image {
    private Image image;

    public ProxyImage(Image image) {
        this.image = image;
    }

    @Override
    public void show() {
        //只有当真正需要调用image的show方法时才创建被代理对象
        if (image == null) {
            image = new RealImage();
            image.show();
        } else {
            System.out.println("此业务无序绘制图片");
        }
    }
}

//指定接口
interface Lunch {
    void eat(String food, Integer num);
}

//真实实现类
class RealLunch implements Lunch {

    @Override
    public void eat(String food, Integer num) {
        System.out.println("我要吃" + num + "分量的" + food);
    }
}

//拦截器
class TxUtil {
    public void preTx() {
        System.out.println("======午饭开始前======");
    }

    public void afterTx() {
        System.out.println("======午饭吃完后======");
    }
}

//InvocationHandler实现类
class MyInvokationHandle implements InvocationHandler {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TxUtil tx = new TxUtil();
        tx.preTx();
        Object result = method.invoke(target, args);//通过反射调用真实类的实现方法
        tx.afterTx();
        return result;
    }
}

//动态代理工厂类
class MyProxyFactory {
    public static Object getProxy(Object target) {
        MyInvokationHandle handle = new MyInvokationHandle();
        handle.setTarget(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handle);
    }
}

public class TestDemo29 {
    private static void code1() {
        long start = System.currentTimeMillis();
        //程序返回一个image对象，该对象只是RealImage的代理对象
        Image image = new ProxyImage(null);
        long mid = System.currentTimeMillis();
        System.out.println("系统得到image对象的时间开销：" + (mid - start));
        image.show();
        long end = System.currentTimeMillis();
        System.out.println("系统完成业务的时间开销：" + (end - mid));
    }

    public static void main(String[] args) {
        Lunch target = new RealLunch();
        Lunch lunch = (Lunch) MyProxyFactory.getProxy(target);
        lunch.eat("宫保鸡丁", 10);
        lunch.eat("水煮肉片", 15);
    }
}
