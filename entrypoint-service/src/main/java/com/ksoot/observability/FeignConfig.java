package com.ksoot.observability;

import feign.RequestInterceptor;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.propagation.TextMapPropagator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

  @Bean
  public RequestInterceptor feignTracingInterceptor() {
    TextMapPropagator propagator = W3CTraceContextPropagator.getInstance();
    return requestTemplate ->
        propagator.inject(
            Context.current(),
            requestTemplate,
            (carrier, key, value) -> carrier.header(key, value));
  }
}
