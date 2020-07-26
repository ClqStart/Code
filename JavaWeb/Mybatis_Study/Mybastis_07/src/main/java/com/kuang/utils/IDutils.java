package com.kuang.utils;

import org.junit.Test;

import java.util.UUID;

public class IDutils  {

    public  static  String  getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public static void main(String[] args) {
        System.out.println(IDutils.getId());
    }

}
