package com.rjs5613.networking.tcp.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleTcpServer implements Closeable {

  private volatile boolean running;
  private final ExecutorService processor;
  private final int port;

  public SimpleTcpServer(int port) {
    this.processor = Executors.newFixedThreadPool(2);
    this.port = port;
  }

  public void run() {
    try (ServerSocket server = new ServerSocket(port)) {
      while (running) {
        Socket clientConnection = server.accept();
        this.processor.submit(new RequestWorker(clientConnection));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public synchronized void start() {
    this.running = true;
    this.run();
  }

  @Override
  public void close() {
    this.running = false;
    this.processor.shutdown();
  }

  private static class RequestWorker implements Runnable {

    private static final String EXIT_COMMAND = "EXIT";
    private final Socket clientConnection;

    public RequestWorker(Socket clientConnection) {
      this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
      try (BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream()))) {
        BufferedReader br = new BufferedReader(new InputStreamReader(clientConnection.getInputStream()));
        String request = br.readLine();
        while (request != null) {
          RequestProcessor requestProcessor = new StandardRequestProcessor(request);
          processRequest(output, requestProcessor);
          request = br.readLine();
          if (EXIT_COMMAND.equalsIgnoreCase(request)) {
            break;
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    private void processRequest(BufferedWriter output, RequestProcessor requestProcessor) throws IOException {
      try {
        Request request = requestProcessor.process();
        Response response = RequestExecutor.execute(Commands.create(request.getAttributes()));
        output.write(response.toString() + "\n\n");
        output.flush();
      } catch (Exception e) {
        output.write(Response.error(e).toString() + "\n\n");
        output.flush();
      }
    }
  }

  public static void main(String[] args) {
    try (SimpleTcpServer s = new SimpleTcpServer(12345)) {
      s.start();
    }
  }
}
