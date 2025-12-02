package com.bank.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Global logging filter for all gateway requests.
 * Logs incoming requests and outgoing responses
 */
@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    /**
     * Filter method that logs request details.
     * @param exchange the current server exchange
     * @param chain provides a way to delegate to the next filter
     * @return Mono<Void> to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(final ServerWebExchange exchange,
                             final GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        String method = exchange.getRequest().getMethod().toString();

        log.info("Incoming request: {} {}", method, path);

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    int statusCode = exchange.getResponse().getStatusCode() != null
                            ? exchange.getResponse().getStatusCode().value()
                            : 0;
                    log.info("Response status: {} for {} {}", statusCode, method, path);
                }));
    }

    /**
     * Get the order value of this filter.
     * @return order value
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}