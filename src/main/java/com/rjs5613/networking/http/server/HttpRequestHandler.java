package com.rjs5613.networking.http.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpRequestHandler implements Runnable {

  private final Socket clientConnection;

  public HttpRequestHandler(Socket clientConnection) {
    this.clientConnection = clientConnection;
  }

  @Override
  public void run() {
    try {
      InputStream inputStream = clientConnection.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      HttpRequestProcessor reqProcessor = new HttpRequestProcessor(br);
      HttpRequest process = reqProcessor.process();
      Route route = Route.of(process.method(), process.path());
      RequestHandler handler = Router.instance().getHandler(route);
      Response handle = handler.handle(process);
      clientConnection.getOutputStream().write(new HttpResponse(handle).toHttpResponse().getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
