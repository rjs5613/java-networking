package com.rjs5613.networking.tcp.server;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StandardRequestProcessor implements RequestProcessor {

  private final String inputRequest;

  public StandardRequestProcessor(String inputRequest) {
    this.inputRequest = inputRequest;
  }

  @Override
  public Request process() {
    if (StringUtils.isBlank(inputRequest)) {
      throw new InvalidRequestException("Empty Request");
    }
    String[] tokens = inputRequest.split(" ");
    if (tokens.length < 3) {
      throw new InvalidRequestException("Invalid Request - Valid Request : REDIS HGET arg1 arg2.");
    }
    Protocol protocol = Protocol.create(tokens[0].trim());
    Operation operation = Operation.create(tokens[1].trim());
    return new Request(protocol, new Attributes(operation, Arrays.copyOfRange(tokens, 2, tokens.length)));
  }
}
