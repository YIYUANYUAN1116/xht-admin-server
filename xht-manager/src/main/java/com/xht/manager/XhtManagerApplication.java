package com.xht.manager;

import com.xht.manager.properties.MinioProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.xht.manager.mapper")
@EnableConfigurationProperties(value = { MinioProperties.class})
@ComponentScan(basePackages = "com.xht")
public class XhtManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(XhtManagerApplication.class, args);
    }

}
