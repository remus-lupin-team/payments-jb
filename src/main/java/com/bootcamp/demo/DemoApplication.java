package com.bootcamp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        //trying to catch the error from the Card constructor
        try {
            SpringApplication.run(DemoApplication.class, args);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        //SpringApplication.run(DemoApplication.class, args);
    }
}