package com.msc.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name = "currency-exchange-service")
@FeignClient(name = "api-gateway")
public interface CurrencyExchangeClient {
    
    @GetMapping("/api/exchange/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(
        @PathVariable String from,
        @PathVariable String to
    );

}
