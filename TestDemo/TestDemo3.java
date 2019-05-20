package TestDemo;
//反射实现工厂模式
public class TestDemo3 {
    public static void main(String[] args) {
        Fruit fruit = FruitFactory.getFruitInstance("TestDemo.Apple");
        fruit.eat();
    }
}

interface Fruit {
    void eat();
}

class Apple implements Fruit {

    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}

class Orange implements Fruit {

    @Override
    public void eat() {
        System.out.println("吃橘子");
    }
}

class FruitFactory {
    private FruitFactory() {

    }

    public static Fruit getFruitInstance(String className) {
        try {
            Class classz = Class.forName(className);
            try {
                return (Fruit) classz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Factory 生产不了 " + className);
    }
}

