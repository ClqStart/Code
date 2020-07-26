package com.clq.springboot_jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;

@SpringBootTest
class SpringbootJdbcApplicationTests {

    @Autowired
    DataSource  dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println("默认数据源"+dataSource.getClass());
        Connection con = dataSource.getConnection();

        System.out.println("连接对象："+ con);
        if(con != null){

            System.out.println("success");
        }else {

            System.out.println("fail");
        }
        con.close();
    }

}
