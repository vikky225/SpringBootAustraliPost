package com.austpos.postcode.api.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResourceNotFoundExceptionTest {

  @Test
  public void testResourceNotFoundException() {
    ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("something else", new RuntimeException("Something"));
    assertEquals("something else", resourceNotFoundException.getMessage());

    resourceNotFoundException = new ResourceNotFoundException("new message");
    assertEquals("new message", resourceNotFoundException.getMessage());

    resourceNotFoundException = new ResourceNotFoundException(new RuntimeException("runtime error"));
    assertEquals("java.lang.RuntimeException: runtime error", resourceNotFoundException.getMessage());
  }

}
