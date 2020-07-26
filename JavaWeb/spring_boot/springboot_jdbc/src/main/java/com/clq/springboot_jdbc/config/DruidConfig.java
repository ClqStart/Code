package com.clq.springboot_jdbc.config;

/*
 *@author:C1q
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource  druid(){
        return  new DruidDataSource();
    }

    //配置druid监控
    //1.配置管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //创建ServletRegistrationBean 对象
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> initParames = new HashMap<>();
        initParames.put("loginUsername","clq");
        initParames.put("loginPassword","123");
        initParames.put("allow","");
        bean.setInitParameters(initParames);
        return bean;
    }
    //2.配置web监控Filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParames = new HashMap<>();
        initParames.put("exclusions",".css,.js,.html,/druid/*");
        bean.setInitParameters(initParames);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
