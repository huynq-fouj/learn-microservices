package com.msc.currency_conversion_service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversionBean {
    
    private int id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;  
    private BigDecimal quantity;  
    private BigDecimal totalCalculatedAmount;  
    private int port;  

}
