package com.rjs5613.networking.http.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHttpServer implements Closeable {
  private final ServerOption serverOption;
  private volatile boolean running;
  private final ExecutorService dispatcher;

  public SimpleHttpServer(ServerOption serverOption) {
    this.serverOption = serverOption;
    this.dispatcher = Executors.newFixedThreadPool(4);
  }

  public Router routes() {
    return Router.instance();
  }

  public void start() {
    running = true;
    try (ServerSocket server = new ServerSocket(serverOption.port())) {
      while (running) {
        Socket clientConnection = server.accept();
        this.dispatcher.submit(new HttpRequestHandler(clientConnection));
      }
    } catch (IOException e) {
      e.printStackTrace();
      dispatcher.shutdown();
    }
  }

  @Override
  public void close() {
    this.running = false;
    this.dispatcher.shutdown();
  }

  public static void main(String[] args) {
    try (SimpleHttpServer server = new SimpleHttpServer(new HttpServerOptions())) {
      server
          .routes()
          .register(
              Route.of(Method.GET, "/test"),
              data ->
                  new SuccessResponse("Request Successfully Handled for : " + data.completeUrl()))
          .register(
              Route.of(Method.GET, "/"),
              data ->
                  new SuccessResponse("Request Successfully Handled for : " + data.completeUrl()));
      server.start();
      Runtime.getRuntime().addShutdownHook(new Thread(server::close));
    }
  }
}
