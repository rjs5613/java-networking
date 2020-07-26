package com.rjs5613.networking.http.server;

public enum HttpStatus {
  OK(200, "OK"),
  NOT_FOUND(404, "Not Found"),
  ISE(500, "Internal Server Error");

  private final int code;
  private final String description;

  HttpStatus(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public static HttpStatus of(int httpStatus) {
    for (HttpStatus status : HttpStatus.values()) {
      if (status.code == httpStatus) {
        return status;
      }
    }
    return OK;
  }

  public String getDisplayValue() {
    return code + " " + description;
  }
}
