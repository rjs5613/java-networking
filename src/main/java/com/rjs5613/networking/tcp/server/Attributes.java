package com.rjs5613.networking.tcp.server;

public class Attributes {

  private final Operation operation;
  private final String[] args;

  public Attributes(Operation operation, String... args) {
    this.operation = operation;
    this.args = args;
  }

  public Operation getOperation() {
    return operation;
  }

  public String[] getArgs() {
    return args;
  }
}
