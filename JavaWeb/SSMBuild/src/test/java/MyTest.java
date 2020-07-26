import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 *@author:C1q
 */
public class MyTest {

    @Test
    public void test() {

        ClassPathXmlApplicationContext content = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookServiceImpl =(BookService)content.getBean("BookServiceImpl");
        for (Books books : bookServiceImpl.queryAllBook()) {
            System.out.println(books);
        }

    }
}
