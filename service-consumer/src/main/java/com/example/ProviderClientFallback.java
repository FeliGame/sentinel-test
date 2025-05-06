package com.example;

import org.springframework.stereotype.Component;

@Component
public class ProviderClientFallback implements ProviderClient {

    @Override
    public String hello() {
        return "Fallback response due to service unavailability.";
    }
}    