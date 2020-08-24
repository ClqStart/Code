package com.test;

import com.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        Person person = (Person) context.getBean("person");
        Person person1 =  context.getBean("person",Person.class);
        System.out.println(person);
    }
}
