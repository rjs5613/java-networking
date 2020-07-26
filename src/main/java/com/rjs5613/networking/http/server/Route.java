package com.rjs5613.networking.http.server;

import java.util.Objects;
import java.util.StringJoiner;

public class Route {
  private final Method method;
  private final String path;

  private Route(Method method, String path) {
    this.method = method;
    this.path = path;
  }

  public static Route of(Method get, String s) {
    return new Route(get, s);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Route that = (Route) o;
    return method == that.method && path.equals(that.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, path);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Route.class.getSimpleName() + "[", "]")
        .add("method=" + method)
        .add("path='" + path + "'")
        .toString();
  }
}
