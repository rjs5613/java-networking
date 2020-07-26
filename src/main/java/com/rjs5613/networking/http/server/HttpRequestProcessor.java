package com.rjs5613.networking.http.server;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestProcessor  {
  private final BufferedReader br;

  public HttpRequestProcessor(BufferedReader br) {
    this.br = br;
  }

  public HttpRequest process() throws IOException {
    String line = br.readLine();
    while(line == null) {
      line = br.readLine();
    }
    HttpRequest httpRequest = new HttpRequest(line);
    while (line != null) {
      line = br.readLine();
      if(StringUtils.isNotBlank(line)) {
        httpRequest.setHeader(line);
        break;
      }
    }
    return httpRequest;
  }
}
