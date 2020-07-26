package com.rjs5613.networking.tcp.server;

public class Request {

  private final Protocol protocol;
  private final Attributes attributes;

  public Request(Protocol protocol, Attributes attributes) {
    this.protocol = protocol;
    this.attributes = attributes;
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public Attributes getAttributes() {
    return attributes;
  }
}
