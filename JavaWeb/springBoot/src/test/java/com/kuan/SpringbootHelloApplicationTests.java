package com.kuan;


import com.kuan.pojo.Dog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringbootHelloApplicationTests {

    @Autowired
    private Dog dog;

    @Test
    void contextloads(){
        System.out.println(dog);
    }
}
