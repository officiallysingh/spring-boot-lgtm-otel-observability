package com.ksoot.observability;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "edge-client", url = "http://localhost:8091", configuration = FeignConfig.class)
public interface EdgeClient {

  @RequestMapping(method = RequestMethod.GET, value = "/edge-endpoint")
  String edgeEndpoint();
}
