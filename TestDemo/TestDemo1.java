package TestDemo;

import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestDemo1 {
    public static void main(String[] args) {
        //函数式接口的套用
        Supplier<String> supplier = () -> {
            Random random = new Random();
            return String.valueOf(random.nextInt(200));
        };
        Predicate<Supplier<String>> predicate = (s) -> {
            String value = s.get();
            System.out.println(value);
            if (value == null) {
                return false;
            } else {
                return value.length() > 2;
            }
        };
        boolean rs = predicate.test(supplier);
        System.out.println(rs);
    }
}
