package com.msc.currency_exchange_service;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.*;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchangeController {
    
    private final Environment environment;
    private final ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    @Observed(
            name = "user.name",
            contextualName = "currency-exchange",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public ExchangeValue retrieveExchangeValue(
        @PathVariable String from,
        @PathVariable String to
    ) {
        log.info("Currency exchange");
        Optional<ExchangeValue> opte = exchangeValueRepository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
        return opte.get();
    }

    @GetMapping("/currency-exchange/list")
    @Observed(
            name = "user.name",
            contextualName = "currency-exchange-list",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public List<ExchangeValue> retrieveExchangeValue() {
        log.info("Call currency exchange list");
        List<ExchangeValue> evs = exchangeValueRepository
            .findAll()
            .stream()
            .map(item -> mapExchangeValue(item))
            .collect(Collectors.toList());
        return evs;
    }
    
    private ExchangeValue mapExchangeValue(ExchangeValue item) {
        item.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return item;
    }

}
