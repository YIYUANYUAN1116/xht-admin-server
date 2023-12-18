package com.xht.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xht"})
public class XhtManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhtManagerApplication.class, args);
    }

}
