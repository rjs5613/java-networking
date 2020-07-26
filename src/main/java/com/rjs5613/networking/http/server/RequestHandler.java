package com.rjs5613.networking.http.server;

@FunctionalInterface
public interface RequestHandler<T> {
  T handle(HttpRequest request);
}
