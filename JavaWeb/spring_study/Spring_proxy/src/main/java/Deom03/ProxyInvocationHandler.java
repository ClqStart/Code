package Deom03;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private  Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    // 生成得到代理类
    public  Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                rent.getClass().getInterfaces(),this);
    }

    //处理代理实例，并返结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //使用反射机制
        seeHost();
        Object result = method.invoke(rent, args);
        sigh();
        return result;
    }

    public  void seeHost(){
        System.out.println("去看房子");
    }
    public  void  sigh(){
        System.out.println("去签合同");
    }



}
