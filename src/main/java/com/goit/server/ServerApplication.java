package com.goit.server;

import com.goit.conf.LoggingConfiguration;

import java.io.IOException;

public class ServerApplication {
    public static void main(String[] args) throws IOException {
        new LoggingConfiguration();
        SimpleHttpServer server = new SimpleHttpServer(8080,"/",4);
        server.start();
    }
}
