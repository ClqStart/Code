package Deom01;

public class Client  {
    public static void main(String[] args) {

        Rent proxy = new proxy(new Host());

        proxy.rent();
    }
}
