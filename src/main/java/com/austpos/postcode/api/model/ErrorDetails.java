package com.austpos.postcode.api.model;

public class ErrorDetails {

  private String detail;
  private String message;

  public ErrorDetails(Exception ex, String detail) {
    this.message = ex.getLocalizedMessage();
    this.detail = detail;
  }

  public String getDetail() {
    return detail;
  }

  public String getMessage() {
    return message;
  }
}
