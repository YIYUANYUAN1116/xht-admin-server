package com.xht.manager;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan("com.xht.manager.mapper")
public class XhtManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhtManagerApplication.class, args);
    }

}
