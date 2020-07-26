package com.rjs5613.networking.http.server;

import java.util.Arrays;

import com.rjs5613.networking.tcp.server.InvalidRequestException;

public enum Method {
  GET,
  PUT,
  POST,
  DELETE;

  public static Method get(String value) {
    for (Method method : Method.values()) {
      if (method.name().equals(value)) {
        return method;
      }
    }
    throw new InvalidRequestException("Allowed Methods : " + Arrays.toString(Method.values()));
  }
}
