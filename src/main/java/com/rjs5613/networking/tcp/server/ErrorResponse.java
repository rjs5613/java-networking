package com.rjs5613.networking.tcp.server;

public class ErrorResponse implements Response {

  private final Exception exception;

  public ErrorResponse(Exception e) {
    this.exception = e;
  }

  @Override
  public String toString() {
    return "Status: Failure\nReason: " + exception.getMessage();
  }
}
