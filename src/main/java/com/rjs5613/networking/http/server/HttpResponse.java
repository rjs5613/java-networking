package com.rjs5613.networking.http.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

  private final String body;
  private final Map<String, String> headers;

  public HttpResponse(Object handle) {
    this.body = handle.toString();
    this.headers = new HashMap<>();
    updateDefaultHeaders();
  }

  private void updateDefaultHeaders() {
    headers.put("Content-Type", "application/json");
    headers.put("Date", new Date().toString());
    headers.put("Server", "SimpleHttpServer");
    try {
      headers.put("host", InetAddress.getLocalHost().toString());
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }
  }

  public HttpResponse header(String key, String value){
    this.headers.put(key, value);
    return this;
  }

  public String toHttpResponse() {
    String bodyJson = "{\"result\":\""+body+"\"}";
    headers.put("Content-Length", Integer.toString(bodyJson.length()));
    StringBuilder finalResponse = new StringBuilder("\nHTTP/1.1 200 OK\n");
    headers.forEach((key, value) -> finalResponse.append(key).append(": ").append(value).append("\n"));
    finalResponse.append("\r\n");
    finalResponse.append(bodyJson);
    return finalResponse.toString();
  }
}
