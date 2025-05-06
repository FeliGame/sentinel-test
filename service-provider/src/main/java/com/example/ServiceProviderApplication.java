package com.example;

import com.alibaba.cloud.sentinel.SentinelWebAutoConfiguration;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@RestController
public class ServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderApplication.class, args);
    }

    @GetMapping("/hello")
    @SentinelResource(value = "helloResource")
    public CompletableFuture<String> hello() {
        return asyncHello();
    }

    @Async
    public CompletableFuture<String> asyncHello() {
        try {
            // 模拟网络拥塞，延时 2 秒
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture("Hello from service provider!");
    }
}    