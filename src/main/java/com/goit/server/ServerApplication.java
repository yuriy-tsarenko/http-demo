package com.goit.server;

import com.goit.conf.LoggingConfiguration;

import java.io.IOException;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        new LoggingConfiguration();
        final int port = 8080;
        final String path = "/";
        final int nThreads = 4;
        SimpleHttpServer server = new SimpleHttpServer(port, path, nThreads);
        server.start();
    }
}
