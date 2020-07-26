package com.rjs5613.networking.http.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpResponse {

  private final Object body;
  private final Map<String, String> headers;
  private final HttpStatus httpStatus;

  public HttpResponse(Response handle) {
    if(Objects.nonNull(handle)) {
      this.body = handle.result();
      this.httpStatus = handle.httpStatus();
    } else {
      this.body = null;
      this.httpStatus = HttpStatus.OK;
    }

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
    StringBuilder finalResponse = new StringBuilder("\n").append("HTTP/1.1 ").append(httpStatus.getDisplayValue()).append("\n");
    headers.forEach((key, value) -> finalResponse.append(key).append(": ").append(value).append("\n"));
    finalResponse.append("\r\n");
    finalResponse.append(bodyJson);
    return finalResponse.toString();
  }
}
