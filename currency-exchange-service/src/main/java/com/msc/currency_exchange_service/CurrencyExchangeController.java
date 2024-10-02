package com.msc.currency_exchange_service;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.*;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {
    
    private final Environment environment;
    private final ExchangeValueRepository exchangeValueRepository;

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(
        @PathVariable String from,
        @PathVariable String to
    ) {
        Optional<ExchangeValue> opte = exchangeValueRepository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
        return opte.get();
    }

    @GetMapping("currency-exchange/list")
    public List<ExchangeValue> retrieveExchangeValue() {
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
