package com.msc.api_gateway;

import io.micrometer.observation.annotation.Observed;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.springframework.http.server.reactive.ServerHttpRequest;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class GatewayLoggingFilter implements GlobalFilter, Ordered  {

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    @Observed(
            name = "user.name",
            contextualName = "from-gateway",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info("Request: {} - Request Uri: {}", request, request.getURI().getPath());
        // String traceId = MDC.get("traceId");
        // String spanId = MDC.get("spanId");
        // log.info("Request passed through API Gateway with traceId: {} and spanId: {}", traceId, spanId);
        return chain.filter(exchange);
    }
    
}
