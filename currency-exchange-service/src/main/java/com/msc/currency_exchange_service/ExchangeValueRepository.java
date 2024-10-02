package com.msc.currency_exchange_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Integer> {
    
    Optional<ExchangeValue> findByFromAndTo(String from, String to);
    List<ExchangeValue> findByFrom(String from);

}
