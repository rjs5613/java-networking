package com.rjs5613.networking.tcp.server;

public class RequestExecutor {
  private RequestExecutor() {

  }

  public static <T> Response execute(Command<T> redisCommand) {
    return Response.success(redisCommand.execute());
  }
}
