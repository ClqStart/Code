package com.bean;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean {
    public Object getObject() throws Exception {
        Person person = new Person();
        person.setAge(18);
        person.setId(2);
        person.setName("王五");
        return person;

    }

    public Class<?> getObjectType() {
        return Person.class;
    }

    public boolean isSingleton() {
        return false;
    }
}
