package com.kuang.pojo;

/*
 *@author:C1q
 */

public class Department {

    private  Integer id;
    private  String  depertmentName;

    public  Department(){}
    public Department(Integer id, String depertmentName) {
        this.id = id;
        this.depertmentName = depertmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepertmentName() {
        return depertmentName;
    }

    public void setDepertmentName(String depertmentName) {
        this.depertmentName = depertmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", depertmentName='" + depertmentName + '\'' +
                '}';
    }
}
