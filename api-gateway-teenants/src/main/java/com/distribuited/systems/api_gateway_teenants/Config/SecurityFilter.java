package com.distribuited.systems.api_gateway_teenants.Config;

import java.time.Duration;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.distribuited.systems.api_gateway_teenants.Repositories.ApiKeyRepository;

import reactor.core.publisher.Mono;

@Component
public class SecurityFilter implements GlobalFilter {
    
    private final ReactiveRedisOperations<String, String> reactiveRedisOperations;
    private final ApiKeyRepository apiKeyRepository;

    SecurityFilter(ReactiveRedisOperations<String, String> reactiveRedisOperations,
        ApiKeyRepository apiKeyRepository
    ) {
        this.reactiveRedisOperations = reactiveRedisOperations;
        this.apiKeyRepository = apiKeyRepository;
    }

    /* 
    Exchange en este caso representa la peticion HTTP, pero wrapped para que podamos 
    extraer atributos como si fuera un objeto.
    */

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String apiKey = exchange.getRequest().getHeaders().getFirst("X-Api-Key");

        if(apiKey == null){
            return unauthorized(exchange);
        }

        return reactiveRedisOperations.opsForValue().get("api-key:" + apiKey)
        .switchIfEmpty(
            Mono.<String>fromCallable(() ->
                apiKeyRepository.findByIdTeenantByApiKey(apiKey)
            ).flatMap(teenant ->{
                if(teenant == null){
                    return Mono.empty();
                }

                return reactiveRedisOperations.opsForValue()
                    .set("api-key:" + apiKey, teenant, Duration.ofHours(2))
                    .then(Mono.just(teenant));
            })
        ).flatMap(teenant ->{
            exchange.getAttributes().put("teenant:", teenant);
            return chain.filter(exchange);
        })
        .switchIfEmpty(unauthorized(exchange));
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().writeWith(Mono.empty());
    }

    
}
