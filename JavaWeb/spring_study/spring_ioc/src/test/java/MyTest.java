import com.kuang.dao.UserDaoImpl;
import com.kuang.dao.UserDaoMysqlImpl;
import com.kuang.dao.UserDaoOracalImpl;
import com.kuang.service.UserService;
import com.kuang.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    public static void main(String[] args) {

    ApplicationContext contex = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl userServiceImpl =(UserServiceImpl)contex.getBean("UserServiceImpl3");
        userServiceImpl.getUser();


//        UserService userService = new UserServiceImpl();
////        ((UserServiceImpl)userService).setUserDao(new UserDaoMysqlImpl());
//       ((UserServiceImpl)userService).setUserDao(new UserDaoOracalImpl());
////        ((UserServiceImpl)userService).setUserDao(new UserDaoImpl());
//        userService.getUser();
    }
}
