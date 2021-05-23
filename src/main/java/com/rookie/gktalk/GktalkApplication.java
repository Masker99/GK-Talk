package com.rookie.gktalk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.rookie.gktalk.mapper")
public class GktalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(GktalkApplication.class, args);
    }

}
