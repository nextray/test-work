package com.test.mygateway.controllers;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GatewayController {
    private final DiscoveryClient discoveryClient;
    RestTemplate restTemplate = new RestTemplate();

    public GatewayController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @RequestMapping("/get")
    public Object serviceInstancesByApplicationName(
            @RequestParam(defaultValue = "-1") Long kladr,
            @RequestParam(defaultValue = "false")  Boolean db
    ) {
        List<ServiceInstance> instances;
        if (db) {
            instances = discoveryClient.getInstances("database-api");
        } else {
            instances = discoveryClient.getInstances("kladr-web-api");
        }
        ServiceInstance instance = instances.stream().findFirst().orElseThrow(() -> new RuntimeException("not found"));
        String url = String.format("%s/get?kladr=%s", instance.getUri(), kladr);
        return restTemplate.getForObject(url, Object.class);
    }

    @RequestMapping("/add")
    public Object addToDatabase(
            @RequestParam(defaultValue = "1") Long kladr,
            @RequestParam(defaultValue = "Пермский Край->Оханский Район->Замании Территория СНП")  String address
    ){
        ServiceInstance instance = discoveryClient.getInstances("database-api")
                .stream()
                .findFirst()
                .orElseThrow(()->new RuntimeException("not found"));
        String url = String.format("%s/add?kladr=%s&address=%s", instance.getUri(), kladr, address);
        return restTemplate.getForObject(url, String.class);
    }
}
