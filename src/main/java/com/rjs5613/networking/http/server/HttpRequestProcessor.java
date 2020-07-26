package com.rjs5613.networking.http.server;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class HttpRequestProcessor {
  private final BufferedReader br;

  public HttpRequestProcessor(BufferedReader br) {
    this.br = br;
  }

  public HttpRequest process() throws IOException {
    String line = br.readLine();
    while (StringUtils.isBlank(line)) {
      line = br.readLine();
    }
    HttpRequest httpRequest = new HttpRequest(line);
    while (StringUtils.isNotBlank(line)) {
      line = br.readLine();
      if (StringUtils.isNotBlank(line)) {
        httpRequest.setHeader(line);
      }
    }
    //TODO: Implement Body Reader
    /*
    while (StringUtils.isBlank(line)) {
      line = br.readLine();
    }
    StringBuilder requestBody = new StringBuilder(line);
    line = br.readLine();
    while (line != null) {
      requestBody.append(line.trim());
      line = br.readLine();
    }
    httpRequest.setBody(requestBody.toString());*/
    return httpRequest;
  }
}
