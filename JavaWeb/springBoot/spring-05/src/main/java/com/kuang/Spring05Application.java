package com.kuang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//@MapperScan("com.kuang.mapper")
public class Spring05Application {

    public static void main(String[] args) {
        SpringApplication.run(Spring05Application.class, args);
    }

}
