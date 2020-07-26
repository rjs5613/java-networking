package com.rjs5613.networking.http.server;

public class Response {
  private final Object body;
  private final HttpStatus httpStatus;

  public Response(Object body, int httpStatus) {
    this.body = body;
    this.httpStatus = HttpStatus.of(httpStatus);
  }

  public Object result() {
    return this.body;
  }

  public HttpStatus httpStatus() {
    return this.httpStatus;
  }
}
