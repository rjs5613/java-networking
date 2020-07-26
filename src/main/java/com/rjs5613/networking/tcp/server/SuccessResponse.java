package com.rjs5613.networking.tcp.server;

public class SuccessResponse implements Response {

  private final Object result;

  public SuccessResponse(Object result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "Status : Success\nResult: " + result.toString();
  }
}
