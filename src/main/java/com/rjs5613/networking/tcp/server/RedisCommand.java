package com.rjs5613.networking.tcp.server;

import java.util.Arrays;

public class RedisCommand implements Command<String> {

  private final Attributes attributes;

  public RedisCommand(Attributes attributes) {
    this.attributes = attributes;
  }

  @Override
  public String execute() {
    return String.format("Successfully Executed: %s %s", attributes.getOperation(), Arrays.toString(attributes.getArgs()));
  }
}
