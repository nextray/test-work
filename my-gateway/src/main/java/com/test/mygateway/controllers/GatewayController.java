package com.test.mygateway.controllers;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
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
        System.out.println(url);
        return restTemplate.getForObject(url, Object.class);
    }

    @RequestMapping("/services")
    public List<String> allServices() {
        return discoveryClient.getServices();
    }

    @RequestMapping("/services/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
