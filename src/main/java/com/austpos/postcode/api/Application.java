package com.austpos.postcode.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  /**
   * Main method, used to run the application.
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
    return applicationBuilder.sources(Application.class);
  }
}

