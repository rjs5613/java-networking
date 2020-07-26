package com.rjs5613.networking.tcp.server;

import java.util.HashMap;
import java.util.Map;

public class Redis {

  private static final Redis REDIS = new Redis();
  private Map<String, Object> map = new HashMap<>();

  public Integer put(String key, String value) {
    map.put(key, value);
    return 1;
  }

  public Object get(String key) {
    if(map.containsKey(key)){
      return map.get(key);
    } else {
      throw new RuntimeException("Key doesn't exists");
    }
  }

  public static Redis instance() {
    return REDIS;
  }
}
