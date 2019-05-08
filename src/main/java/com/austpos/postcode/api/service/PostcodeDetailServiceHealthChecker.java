package com.austpos.postcode.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Health check
 */
@Component
public class PostcodeDetailServiceHealthChecker implements HealthIndicator {

  @Autowired
  private ServiceProperties configuration;

  @Override
  public Health health() {
    return Health.up().withDetail("details", "{ 'internals' : 'server is running fine', 'profile' : '" +
        this.configuration.getName() + "' }").status("OK").build();
  }
}
