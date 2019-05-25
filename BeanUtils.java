

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Objects;

public class BeanUtils {
    //题目描述：通过反射赋值源对象中的属性值到目标对象的具有相同类型和名称的属性
    /**
     * 对象的属性值拷贝
     * <p>
     * 将source对象中的属性值赋值到target对象中的属性，属性名一样，类型一样
     * <p>
     * example:
     * <p>
     * source:
     * <p>
     * String name;
     * String address;
     * Integer age;
     * Date   birthday;
     * <p>
     * target:
     * String name;
     * String address;
     * String email
     * <p>
     * 结果： source name, address -> target name, address
     *
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target)  {
        //TODO
        //1.参数校验
        if (source == null) {
            throw new IllegalArgumentException("Source object must be not null.");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target object must be not null.");
        }
        //2.获取Class对象
       Class cls = source.getClass();
       Class clz = target.getClass();
        //3.获取Class对象中的Field
       Field[] fields = cls.getDeclaredFields();
       Field[] fields1 = clz.getDeclaredFields();
        //4. 通过sourceFields在targetFields找它元素相同（名字和类型）
        for (Field field : fields) {
            for (Field aFields1 : fields1) {
                if (field.getName().equals(aFields1.getName()) &&
                        field.getType() == aFields1.getType()) {
                    field.setAccessible(true);
                    aFields1.setAccessible(true);
                    Object object;
                    try {
                        object = field.get(source);
                        aFields1.set(target, object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}