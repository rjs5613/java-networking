package com.rjs5613.networking.http.server;

import java.util.Objects;

public class RequestDefinition {
  private final Method method;
  private final String path;
  public RequestDefinition(Method method, String path) {
    this.method = method;
    this.path = path;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RequestDefinition that = (RequestDefinition) o;
    return method == that.method &&
      path.equals(that.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, path);
  }
}
