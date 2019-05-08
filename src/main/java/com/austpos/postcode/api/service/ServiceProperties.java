package com.austpos.postcode.api.service;

import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configurable service properties
 */
@ConfigurationProperties(prefix = "postcode.service", ignoreUnknownFields = false)
@Component
public class ServiceProperties {

  @NotNull
  private String name = "Default";

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
