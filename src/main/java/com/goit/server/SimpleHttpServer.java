package com.goit.server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Slf4j
public class SimpleHttpServer {
    HttpServer server;

    public SimpleHttpServer(int port, String path, int nThreads) throws IOException {
        InetSocketAddress localhost = new InetSocketAddress("localhost", port);
        HttpServer httpServer = HttpServer.create(localhost, 0);
        httpServer.createContext(path, new SimpleHandler());
        httpServer.setExecutor(Executors.newFixedThreadPool(nThreads));
        server = httpServer;
    }

    public void start() {
        server.start();
        log.info("Started http server, listening port:{}", server.getAddress().getPort());
    }
}
