package com.rjs5613.networking.tcp.server;

import java.util.Arrays;

public enum Protocol {
  REDIS;

  public static Protocol create(String trim) {
    for (Protocol protocol : Protocol.values()) {
      if (protocol.name().equalsIgnoreCase(trim)) {
        return protocol;
      }
    }
    throw new InvalidRequestException("Supported protocols are : " + Arrays.toString(Protocol.values()));
  }
}
