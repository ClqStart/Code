import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class MyT {

    public static void main(String[] args) throws SQLException {

        ApplicationContext context = new ClassPathXmlApplicationContext("ioc_io.xml");

        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);

        System.out.println(dataSource);
        System.out.println(dataSource.getConnection());
    }
}
