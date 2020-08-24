import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

public class TestIO {

   ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public  void  test01() throws SQLException {
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource.getConnection());
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        System.out.println(template);

    }
}
