package com.sx.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CmnApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmnApplication.class,args);
    }
}
