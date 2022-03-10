package com.jie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jie.mapper")
public class SpringsecurityJwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityJwtDemoApplication.class, args);
    }

}
