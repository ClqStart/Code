package com.proxy;

public class test {
    public static void main(String[] args) {
      //  System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Calculator proxy = CalculatorProxy.getProxy(new MyCalculator());
        System.out.println(proxy.add(1,1));
        System.out.println(proxy.getClass());
    }
}