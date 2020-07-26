package com.rjs5613.networking.http.server;

import com.rjs5613.networking.tcp.server.InvalidRequestException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

  private final Method method;
  private final String completeUrl;
  private final String path;
  private final HashMap<String, String> queryParams;
  private final Map<String, String> headers;

  private String body;

  public HttpRequest(String line) {
    String[] values = line.split(" ");
    if(values.length<3) {
      throw new InvalidRequestException("");
    }
    this.method = Method.get(values[0]);
    this.completeUrl = values[1];
    String[] split = this.completeUrl.split("\\?");
    this.path = split[0];
    this.queryParams = new HashMap<>();
    this.headers = new HashMap<>();
    if(split.length>1) {
      String queryString = split[1];
      String[] split1 = queryString.split("&");
      Arrays.stream(split1).forEach(queryPart ->{
        String[] query = queryPart.split("=");
        queryParams.put(query[0], query[1]);
      });
    }
  }

  public Method method(){
    return method;
  }

  public String path() {
    return path;
  }

  public Map<String, String> queryParams() {
    return queryParams;
  }

  public Map<String, String> headers() {
    return headers;
  }

  public void setHeader(String headerString) {
    String[] split = headerString.split(":");
    headers.put(split[0].trim(), headerString.substring(split[0].length()+2));
  }

  public String completeUrl() {
    return completeUrl;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String toString) {
    this.body = toString;
  }
}
