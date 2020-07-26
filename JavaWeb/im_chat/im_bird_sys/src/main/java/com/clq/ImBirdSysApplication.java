package com.clq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value = "com.clq.mapper")
@ComponentScan(basePackages = {"com.clq","com.idworker"})
public class ImBirdSysApplication  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ImBirdSysApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ImBirdSysApplication.class, args);
    }

}
