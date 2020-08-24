import com.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");

//        Person pesron = context.getBean("person", Person.class);
//        System.out.println(pesron);

        Person pesron2 = context.getBean("person2", Person.class);
        Person pesron3 = context.getBean("person3", Person.class);
        System.out.println(pesron2);
        System.out.println(pesron3);

        Person person = context.getBean("myfactoryBean", Person.class);
        System.out.println(person);
    }
}
