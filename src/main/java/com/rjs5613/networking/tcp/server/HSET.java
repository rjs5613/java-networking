package com.rjs5613.networking.tcp.server;

public class HSET implements Command<Integer> {

  private final String key;
  private final String value;

  HSET(String key, String value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public Integer execute() {
    return Redis.instance().put(key, value);
  }
}
