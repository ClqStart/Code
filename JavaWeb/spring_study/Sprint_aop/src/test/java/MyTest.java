import com.kuang.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserService userService = context.getBean("UserServiceImp", UserService.class);
        UserService serviceImp = (UserService) context.getBean("UserServiceImp");
        serviceImp.delete();
    }
}
