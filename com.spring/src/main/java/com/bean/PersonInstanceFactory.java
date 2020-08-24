package com.bean;

public class PersonInstanceFactory {



    public Person getPerson(String name){
            Person person = new Person();
            person.setAge(18);
            person.setId(2);
            person.setName(name);
            return person;


    }



}
