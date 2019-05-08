package com.austpos.postcode.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceNotModifiableException extends RuntimeException {
  public ResourceNotModifiableException() {
    super();
  }

  public ResourceNotModifiableException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotModifiableException(String message) {
    super(message);
  }

  public ResourceNotModifiableException(Throwable cause) {
    super(cause);
  }
}
