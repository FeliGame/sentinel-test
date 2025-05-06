package com.example;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-provider", url = "http://localhost:8080", fallback = ProviderClientFallback.class)
public interface ProviderClient {

    @GetMapping("/hello")
    String hello();
}    