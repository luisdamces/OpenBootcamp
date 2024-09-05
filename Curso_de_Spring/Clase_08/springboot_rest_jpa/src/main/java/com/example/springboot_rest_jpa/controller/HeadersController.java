package com.example.springboot_rest_jpa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("unused")
public class HeadersController {

    // User Agent Detect v1 (UAD)
    @GetMapping("/headers/uad1")
    public String getUserAgent1(@RequestHeader("User-Agent") String userAgent) {
        return userAgent;
    }

    // User Agent Detect 2 (UAD)
    @GetMapping("/headers/uad2")
    public List<String> getUserAgent2(@RequestHeader HttpHeaders headers) {
        return headers.get("user-agent");
    }

    // Get all header
    @GetMapping("/headers")
    public Map<String,String> getHeader(@RequestHeader Map<String,String> header) {
        return header;
    }

}
