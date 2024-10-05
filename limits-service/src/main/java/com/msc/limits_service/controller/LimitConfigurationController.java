package com.msc.limits_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.msc.limits_service.bean.LimitConfiguration;
import com.msc.limits_service.config.Configuration;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LimitConfigurationController {
    
    private final Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfiguration() {
        return new LimitConfiguration(
            configuration.getMaximum(),
            configuration.getMinimum()
        );
    }

    @GetMapping("/error-limits")
    @CircuitBreaker(name = "serviceA", fallbackMethod = "fallBack")
    public LimitConfiguration retriveLimitsFromConfigurationError() {
        throw new RuntimeException("Some thing when wrong");
    }

    public LimitConfiguration fallBack(Exception ex) {
        log.info("Fall back error: {}", ex.getMessage());
        return new LimitConfiguration(100, 10);
    }
    
}
