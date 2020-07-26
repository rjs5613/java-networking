package com.rjs5613.networking.http.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Router {

  private static final Router REQUEST_MAPPER = new Router();
  private final Map<Route, RequestHandler> map;
  private RequestHandler notFoundHandler;

  private Router() {
    map = new HashMap<>();
  }

  public static Router instance() {
    return REQUEST_MAPPER;
  }

  public Router register(Route route, RequestHandler handler) {
    this.map.put(route, handler);
    return this;
  }

  public Router registerNotFoundHandler(RequestHandler handler) {
    this.notFoundHandler = handler;
    return this;
  }

  private RequestHandler getNotFoundHandler() {
    return Objects.requireNonNullElseGet(
        this.notFoundHandler, () -> data -> new Response("No Handler Present", 404));
  }

  public RequestHandler getHandler(Route route) {
    if (map.containsKey(route)) {
      return this.map.get(route);
    }
    return getNotFoundHandler();
  }

  public RequestHandler getErrorHandler(Exception e) {
    return data-> new Response(e.getMessage(), 500);
  }
}
