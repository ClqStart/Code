package Deom02;

public class Client {
    public static void main(String[] args) {

        //真是角色
        UserServiceImpl service = new UserServiceImpl();


        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(service);

        UserService proxy = (UserService) pih.getProxy();

        proxy.add();
        proxy.delete();
        proxy.update();
        proxy.query();

    }
}
