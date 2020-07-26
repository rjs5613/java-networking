package com.rjs5613.networking.http.server;

@FunctionalInterface
public interface RequestHandler {
  Response handle(HttpRequest request);
}
