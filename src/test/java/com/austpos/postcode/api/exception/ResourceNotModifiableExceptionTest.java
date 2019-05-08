package com.austpos.postcode.api.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResourceNotModifiableExceptionTest {

  @Test
  public void testResourceNotFoundException() {
    ResourceNotModifiableException resourceNotModifiableException = new ResourceNotModifiableException("something else", new RuntimeException("Something"));
    assertEquals("something else", resourceNotModifiableException.getMessage());

    resourceNotModifiableException = new ResourceNotModifiableException("new message");
    assertEquals("new message", resourceNotModifiableException.getMessage());

    resourceNotModifiableException = new ResourceNotModifiableException(new RuntimeException("runtime error"));
    assertEquals("java.lang.RuntimeException: runtime error", resourceNotModifiableException.getMessage());
  }

}
