package com.proxy;

import com.sun.org.apache.bcel.internal.generic.RET;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {


    public static  Calculator getProxy( final  Calculator calculator){

        ClassLoader classLoader = calculator.getClass().getClassLoader();

        Class<?>[] inerface= calculator.getClass().getInterfaces();


        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = method.invoke(calculator, args);
                return  result;
            }
        };

        Object o = Proxy.newProxyInstance(classLoader, inerface, h);
        return (Calculator) o;


    }



}
