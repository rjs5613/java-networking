package com.rjs5613.networking.tcp.server;

public interface Response {

  static Response error(Exception e) {
    return new ErrorResponse(e);
  }

  static Response success(Object result) {
    return new SuccessResponse(result);
  }
}
