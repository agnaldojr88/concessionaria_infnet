package com.agnjr.concessionaria.controller;

import com.agnjr.concessionaria.apiclient.ExternalApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api_ext/")
public class ExternalApiController {

    @Autowired
    private ExternalApiClient externalApiClient;

    @GetMapping("/posts")
    public ResponseEntity<List<Map<String, Object>>> getPosts() {
        List<Map<String, Object>> posts = externalApiClient.getPosts();
        return ResponseEntity.ok(posts);
    }

}
