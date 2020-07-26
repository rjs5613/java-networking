package com.rjs5613.networking.http.server;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

  public static final RequestMapper REQUEST_MAPPER = new RequestMapper();
  private final Map<RequestDefinition, RequestHandler<?>> map;

  private RequestMapper() {
    map = new HashMap<>();
  }

  public static RequestMapper instance() {
    return REQUEST_MAPPER;
  }

  public RequestHandler register(RequestDefinition requestDefinition, RequestHandler handler) {
    return this.map.put(requestDefinition, handler);
  }

  public RequestHandler getHandler(RequestDefinition requestDefinition) {
    return this.map.get(requestDefinition);
  }
}
