package com.sx.yygh.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
//
//@SpringBootApplication
//@ComponentScan(basePackages = {"com.sx"})
//@EnableDiscoveryClient
//@EnableFeignClients(basePackages = {"com.sx"})
@MapperScan("com.sx.yygh.order.mapper")

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("com.sx")
@ComponentScan(basePackages = {"com.sx"})
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}

