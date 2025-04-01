package com.ksoot.observability;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EntryPointApplication {

  public static void main(String[] args) {
    SpringApplication.run(EntryPointApplication.class, args);
  }
}
