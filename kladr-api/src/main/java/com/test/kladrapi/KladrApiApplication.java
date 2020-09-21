package com.test.kladrapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableDiscoveryClient
public class KladrApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KladrApiApplication.class, args);
    }

}
