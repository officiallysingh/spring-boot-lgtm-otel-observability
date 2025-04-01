package com.ksoot.observability;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EntryPointController {

  private final EdgeClient edgeClient;

  private final KafkaTemplate<String, String> kafkaTemplate;

  private final MeterRegistry meterRegistry;

  @GetMapping("/entry-point")
  public String home() {
    log.info("Accessing home endpoint");
    this.meterRegistry.counter("entry_endpoint_visits").increment();
    return "Hello, Observability!";
  }

  @GetMapping("/entry-point/external-call-edge-service")
  public String externalCall() {
    log.warn("Making an external API call to Edge Service");
    this.meterRegistry.counter("external_calls").increment();
    try {
      String response = this.edgeClient.edgeEndpoint();
      return "External Call Successful: " + response;
    } catch (Exception e) {
      log.error("External call failed", e);
      meterRegistry.counter("external_calls_failed").increment();
      return "External Call Failed";
    }
  }

  @GetMapping("/entry-point/external-message-edge-service")
  public String externalMessage() {
    log.warn("Writing Kafka message to be received by Edge Service");
    this.meterRegistry.counter("external_messages").increment();
    try {
      final SendResult<String, String> kafkaResponse =
          this.kafkaTemplate.sendDefault("Hello, Edge Service!").get();
      log.info(
          "Sent message Successfully to Edge Service, with offset=[{}]",
          kafkaResponse.getRecordMetadata().offset());
      return "Kafka message sent Successfully to Edge Service";
    } catch (final Exception e) {
      log.error("Kafka message to Edge Service failed!", e);
      this.meterRegistry.counter("external_messages_failed").increment();
      return "Kafka message to Edge Service failed!";
    }
  }
}
