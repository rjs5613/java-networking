package com.rjs5613.networking.http.server;

import java.util.HashMap;
import java.util.Map;

public class Router {

  private static final Router REQUEST_MAPPER = new Router();
  private final Map<Route, RequestHandler> handlerMapping;

  private Router() {
    handlerMapping = new HashMap<>();
  }

  public static Router instance() {
    return REQUEST_MAPPER;
  }

  public Router register(Route route, RequestHandler handler) {
    this.handlerMapping.put(route, handler);
    return this;
  }

  public RequestHandler getHandler(Route route) {
    if (handlerMapping.containsKey(route)) {
      return this.handlerMapping.get(route);
    }
    return notFoundHandler();
  }

  public RequestHandler serverErrorHandler(Exception e) {
    return data -> new Response(e.getMessage(), 500);
  }

  private RequestHandler notFoundHandler() {
    return data -> new Response("No Handler Present", 404);
  }
}
