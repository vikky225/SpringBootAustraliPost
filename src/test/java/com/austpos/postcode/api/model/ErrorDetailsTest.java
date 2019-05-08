package com.austpos.postcode.api.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ErrorDetailsTest {

  @Test
  public void testErrorDetails() {
    RuntimeException error = new RuntimeException("Something is wrong");
    ErrorDetails details = new ErrorDetails(error, "error message");
    assertEquals("Something is wrong", details.getMessage());
    assertEquals("error message", details.getDetail());
  }

}
