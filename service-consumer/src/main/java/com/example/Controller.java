package com.example;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    @Qualifier("com.example.ProviderClient")
    private ProviderClient providerClient;

    @GetMapping("/call-provider")
    @SentinelResource(value = "callProviderResource", fallback = "fallbackMethod")
    public String callProvider() {
        return providerClient.hello();
    }
    // 熔断、降级时调用方法
    public String fallbackMethod(Throwable throwable) {
        return "Fallback response due to Sentinel rule triggered: " + throwable.getMessage();
    }
}    