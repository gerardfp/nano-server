package com.github.gerardfp;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class NanoServer {

    public interface Response {
        void send(String response);
    }

    public interface Handler {
        void handle(String query, Response response) throws Exception;
    }

    public HttpServer server;

    public static NanoServer create(String ip, int port)  {
        NanoServer nanoServer = new NanoServer();

        try {
            nanoServer.server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return nanoServer;
    }

    public void start() {
        server.start();
    }

    public NanoServer endpoint(String path, Handler handler) {
        server.createContext(path, httpExchange -> {
            String query = "";
            try {
                query = httpExchange.getRequestURI().toString().split("\\?")[1];
            } catch (Exception ignored) {}

            try {
                handler.handle(query, response -> {
                    try {
                        httpExchange.sendResponseHeaders(200, response.length());
                        httpExchange.getResponseBody().write(response.getBytes());
                        httpExchange.getResponseBody().flush();
                        httpExchange.getResponseBody().close();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return this;
    }
}
