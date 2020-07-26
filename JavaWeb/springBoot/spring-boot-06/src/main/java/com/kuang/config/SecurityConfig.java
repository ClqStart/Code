package com.kuang.config;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 *@author:C1q
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    //授权
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().antMatchers("/").permitAll();
       http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("vip2");
       http.authorizeRequests().antMatchers("/error/**").hasAnyRole("vip3");

//       http.formLogin();

//       http.formLogin().loginPage("/tologin").passwordParameter().usernameParameter().loginProcessingUrl();
        http.formLogin().loginPage("/tologin");
       http.csrf().disable();
       http.rememberMe();
       http.logout();
       http.logout().logoutSuccessUrl("/");
       http.rememberMe().rememberMeParameter("remember");
    }
    //认证

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("clq").password(new BCryptPasswordEncoder().encode("123")).roles("vip2","vip2")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip2");
    }
}
