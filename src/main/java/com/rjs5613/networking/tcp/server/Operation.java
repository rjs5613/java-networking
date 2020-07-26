package com.rjs5613.networking.tcp.server;

import java.util.Arrays;

public enum Operation {
  HGET, HGETALL, HSET, DEL;

  public static Operation create(String trim) {
    for (Operation operation : Operation.values()) {
      if (operation.name().equalsIgnoreCase(trim)) {
        return operation;
      }
    }
    throw new InvalidRequestException("Supported Operations are : " + Arrays.toString(Operation.values()));
  }
}
