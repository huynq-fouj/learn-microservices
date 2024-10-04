package com.msc.currency_conversion_service;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CurrencyConversionController {

    private final CurrencyExchangeClient currencyExchangeClient;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    @Observed(
            name = "user.name",
            contextualName = "currency-conversion",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public CurrencyConversionBean getMethodName(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        log.info("Call Currency Exchange");
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
