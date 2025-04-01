package com.ksoot.observability;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EdgeController {

  @GetMapping("/edge-endpoint")
  public String edgeEndpoint() {
    log.info("Accessing Edge endpoint");
    return "Hello, From Edge Service!";
  }
}
