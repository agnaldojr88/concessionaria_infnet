package com.agnjr.concessionaria.apiclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface ExternalApiClient {

    @GetMapping("/posts")
    List<Map<String, Object>> getPosts();

}