import com.kuang.pojo.Hello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext contex = new ClassPathXmlApplicationContext("beans.xml");
        Hello hello = (Hello)contex.getBean("Hello");
        System.out.println(hello.toString());
    }
}
