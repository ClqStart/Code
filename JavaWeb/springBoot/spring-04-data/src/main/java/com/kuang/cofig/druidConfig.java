package com.kuang.cofig;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
 *@author:C1q
 */
@Controller
public class druidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSoource() {
        return new DruidDataSource();
    }


    //后台监控

    @Bean
    public ServletRegistrationBean StatViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        HashMap<String, String> initParameters = new HashMap<>();

        //登录密码
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123");
        //允许访问
        initParameters.put("allow","");

        bean.setInitParameters(initParameters);

        return bean;
    }

    //filter

    public FilterRegistrationBean  webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();

        bean.setFilter(new WebStatFilter());


        Map<String, String> initParameters = new HashMap<>();

//        资源过滤
        initParameters.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);

        return bean;
    }



}
