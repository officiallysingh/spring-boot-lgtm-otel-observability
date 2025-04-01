package com.ksoot.observability;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EdgeMessageListener {

  @KafkaListener(topics = "observability-topic")
  public void listenMessage(String message) {
    log.info("Received Message: " + message);
  }
}
