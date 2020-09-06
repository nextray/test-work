package com.test.kladrapi.controllers;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ServiceAPIController {
    private final DiscoveryClient discoveryClient;
    private String token = "925d9ecf4473e3f99b955273bbcbc02b1a42426c";

    public ServiceAPIController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/get")
    public Object serviceInstancesByApplicationName(@RequestParam(defaultValue = "-1") Long kladr ) {
        RestTemplate restTemplate = new RestTemplate();
        String uriTemplate = "https://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/fias";

        URI uri = UriComponentsBuilder.fromUriString(uriTemplate).build(42);
        Map<String, String> map = new HashMap<>();
        map.put("query",kladr.toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", "Token " + token);
        RequestEntity requestEntity = RequestEntity.post(uri)
                .headers(httpHeaders)
                .body(map);
        System.out.println("requestEntity: ");
        System.out.println(requestEntity.toString());
        ResponseEntity<Object> response = restTemplate.exchange(requestEntity, Object.class);
        return response.getBody();
    }
}
