package Deom01;

public class proxy implements Rent {

    private Rent  host;

    public  proxy(){}
    public  proxy(Rent host){
        this.host = host;
    }
    public void rent() {
        host.rent();
    }
}
