package com.rjs5613.networking.tcp.server;

public class Commands {
  private Commands() {
  }

  public static Command<?> create(Attributes attributes) {
    switch (attributes.getOperation()){
      case HGET:return new GET(attributes.getArgs()[0]);
      case HSET:return new HSET(attributes.getArgs()[0], attributes.getArgs()[1]);
    }
    return new RedisCommand(attributes);
  }
}
