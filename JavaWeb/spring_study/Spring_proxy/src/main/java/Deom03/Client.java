package Deom03;

public class Client {
    public static void main(String[] args) {
        Host host = new Host();

        //代理角色
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setRent(host);

        Rent proxy = (Rent) pih.getProxy();
        proxy.rent();

    }
}

