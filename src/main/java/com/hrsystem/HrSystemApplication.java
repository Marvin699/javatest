package com.hrsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hrsystem.mapper")
public class HrSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrSystemApplication.class, args);
    }
}
