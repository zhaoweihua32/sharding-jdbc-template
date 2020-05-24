package org.example.integration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * @author zhaoweihua
 */
@SpringBootApplication
@MapperScan(basePackages = "org.example.integration.dao")
public class ShardingBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(ShardingBootStrap.class, args);
    }
}
