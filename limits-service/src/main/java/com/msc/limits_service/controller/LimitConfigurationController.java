package com.msc.limits_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.msc.limits_service.bean.LimitConfiguration;
import com.msc.limits_service.config.Configuration;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class LimitConfigurationController {
    
    private final Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retriveLimitsFromConfiguration() {
        return new LimitConfiguration(
            configuration.getMaximum(),
            configuration.getMinimum()
        );
    }
    
}
