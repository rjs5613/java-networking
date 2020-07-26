package com.rjs5613.networking.http.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHttpServer implements Closeable {
  private final ServerOption serverOption;
  private boolean running;
  private final ExecutorService dispatcher;

  public SimpleHttpServer(ServerOption serverOption) {
    this.serverOption = serverOption;
    this.dispatcher = Executors.newFixedThreadPool(4);
  }

  public void start() {
    running = true;
    try(ServerSocket server = new ServerSocket(serverOption.port())) {
        while (running) {
          Socket clientConnection = server.accept();
          this.dispatcher.submit(new HttpRequestHandler(clientConnection));
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void close() throws IOException {
    this.running = false;
    this.dispatcher.shutdown();
  }

  public static void main(String[] args) {
    try(SimpleHttpServer server = new SimpleHttpServer(new HttpServerOptions())){
      server.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
