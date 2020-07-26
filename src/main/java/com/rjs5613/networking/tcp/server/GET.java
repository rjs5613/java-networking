package com.rjs5613.networking.tcp.server;

public class GET implements Command<Object> {

  private final String key;

  public GET(String key) {
    this.key = key;
  }
  @Override
  public Object execute() {
    return Redis.instance().get(key);
  }
}
