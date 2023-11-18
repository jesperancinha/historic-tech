import java.lang.reflect.InvocationTargetException;

class AddJ {

    public static Integer add(Integer a, Integer b) {
        return a+b;
    }

     public static String join(Integer a, Integer b){
        return "%d%d".formatted(a, b);
     }
}

public class CalculatorJava {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var className = args[0] + "J";
        var methodName = args[1];
        var inputA = Integer.parseInt(args[2]);
        var inputB = Integer.parseInt(args[3]);
        var clazz = Class.forName(className);
        System.out.println(inputA);
        System.out.println(inputB);
        System.out.println(clazz);
        var method = clazz.getDeclaredMethod(methodName, Integer.class, Integer.class);
        System.out.println(method);
        var result = method
                .invoke(
                        null,
                        inputA,
                        inputB
                );
        System.out.println(result);
    }
}
