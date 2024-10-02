package com.msc.limits_service.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LimitConfiguration {
    
    private int maximum;
    private int minimum;

}
