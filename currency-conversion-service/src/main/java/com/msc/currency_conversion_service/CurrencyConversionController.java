package com.msc.currency_conversion_service;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {
    
    private final CurrencyExchangeClient currencyExchangeClient;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean getMethodName(
        @PathVariable String from,
        @PathVariable String to,
        @PathVariable BigDecimal quantity
    ) {

        CurrencyConversionBean response = currencyExchangeClient.retrieveExchangeValue(from, to);
        
        return CurrencyConversionBean.builder()
            .from(from)
            .to(to)
            .quantity(quantity)
            .port(response.getPort())
            .id(response.getId())
            .conversionMultiple(response.getConversionMultiple())
            .totalCalculatedAmount(quantity.multiply(response.getConversionMultiple()))
        .build();
    }
    

}
