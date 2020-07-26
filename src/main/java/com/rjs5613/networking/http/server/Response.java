package com.rjs5613.networking.http.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Response {
  private final Object body;
  private final HttpStatus httpStatus;
  private final Map<String, String> headers;

  public Response(Object body, int httpStatus) {
    this.body = body;
    this.httpStatus = HttpStatus.of(httpStatus);
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

  public String toHttpString() {
    String bodyJson = "{\"result\":\"" + body + "\"}";
    headers.put("Content-Length", Integer.toString(bodyJson.length()));
    StringBuilder finalResponse =
        new StringBuilder("\n")
            .append("HTTP/1.1 ")
            .append(httpStatus.getDisplayValue())
            .append("\n");
    headers.forEach(
        (key, value) -> finalResponse.append(key).append(": ").append(value).append("\n"));
    finalResponse.append("\r\n");
    finalResponse.append(bodyJson);
    return finalResponse.toString();
  }
}
