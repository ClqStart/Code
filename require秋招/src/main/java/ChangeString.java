import java.lang.reflect.Field;

public class ChangeString {



    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        String  s = "hello word";
        System.out.println("s: "+s);

        Field declaredField = String.class.getDeclaredField("value");
        declaredField.setAccessible(true);

         char[] value =(char[])declaredField.get(s);
         value[5] = '_';
        System.out.println("s1: "+s);

    }
}
