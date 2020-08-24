package com.bean;

public class PersonStaticFactory {



    public static Person getPerson(String name){
            Person person = new Person();
            person.setAge(15);
            person.setId(1);
            person.setName(name);
            return person;


    }



}
