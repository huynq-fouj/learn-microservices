package com.msc.currency_exchange_service;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tblev")
public class ExchangeValue {
    
    @Id
    @Column(name = "ev_id")
    private int id;
    @Column(name = "ev_from")
    private String from;
    @Column(name = "ev_to")
    private String to;
    @Column(name = "ev_convert_multiple")
    private BigDecimal conversionMultiple;  
    @Column(name = "ev_port")
    private int port;

}
