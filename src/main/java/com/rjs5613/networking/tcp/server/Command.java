package com.rjs5613.networking.tcp.server;

public interface Command<T> {
  T execute();
}
