package com.cglib;

import org.springframework.cglib.proxy.Enhancer;

public class test {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(MyCalculator.class);
        enhancer.setCallback(new MyCglib());

        MyCalculator myCalculator = (MyCalculator) enhancer.create();

        System.out.println(myCalculator.add(1, 1));
        System.out.println(myCalculator.getClass());

    }
}
