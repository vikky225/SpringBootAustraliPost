package com.austpos.postcode.api.rest;

import com.austpos.postcode.api.exception.ResourceNotModifiableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.austpos.postcode.api.exception.ResourceNotFoundException;
import com.austpos.postcode.api.model.ErrorDetails;

public abstract class BaseRestController {

  protected final Logger LOG = LoggerFactory.getLogger(getClass());

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException.class)
  public
  @ResponseBody
  ErrorDetails handleResourceNotFoundException(ResourceNotFoundException ex) {
    LOG.info("ResourceNotFoundException handler:" + ex.getMessage());
    return new ErrorDetails(ex, "Resource cannot be located.");
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(ResourceNotModifiableException.class)
  public
  @ResponseBody
  ErrorDetails handleResourceNotModifiableException(ResourceNotModifiableException ex) {
    LOG.info("ResourceNotModifiableException handler:" + ex.getMessage());
    return new ErrorDetails(ex, "Resource cannot be created.");
  }


  public <T> T checkResourceFound(final T resource) {
    if (resource == null) {
      throw new ResourceNotFoundException("resource not found");
    }
    return resource;
  }

  public <T> T checkResourceAlreadyExist(final T resource) {
    if (resource != null) {
      throw new ResourceNotModifiableException("resource already existed");
    }
    return resource;
  }

}
