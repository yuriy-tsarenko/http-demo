package com.goit.server;

import com.goit.exception.ServerInternalErrorException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

@Slf4j
public class SimpleHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        URI endpoint = exchange.getRequestURI();
        log.debug("consumed request:{}, endpoint:{}", requestMethod, endpoint);
        ResponseEntity responseEntity = null;
        try {
            switch (requestMethod) {
                case "GET":
                    responseEntity = handeGET(exchange);
                    break;
                case "POST":
                    responseEntity = handePOST(exchange);
                    break;
                case "PUT":
                    responseEntity = handePUT(exchange);
                    break;
                case "DELETE":
                    responseEntity = handeDELETE(exchange);
                    break;
                default:
                    throw new ServerInternalErrorException("unsupported http method");
            }
        } catch (Exception e) {
            if (e instanceof ServerInternalErrorException) {
                int httpStatus = ((ServerInternalErrorException) e).getHttpCode();
                String responseBody = e.getMessage();
                responseEntity = ResponseEntity.of(responseBody, httpStatus);
            } else {
                final int status = 500;
                responseEntity = ResponseEntity.of("", status);
            }
        } finally {
            sendResponse(exchange, responseEntity);
        }
    }

    private ResponseEntity handeDELETE(HttpExchange exchange) throws IOException {
        final int status = 204;
        return ResponseEntity.of("deleted", status);
    }

    private ResponseEntity handePUT(HttpExchange exchange) {
        try {
            byte[] bytes = exchange.getRequestBody().readAllBytes();
            log.info(new String(bytes));
        } catch (Exception e) {
            throw new ServerInternalErrorException("can't read request");
        }
        final int status = 200;
        return ResponseEntity.of("updated", status);
    }

    private ResponseEntity handePOST(HttpExchange exchange) {
        try {
            byte[] bytes = exchange.getRequestBody().readAllBytes();
            log.info(new String(bytes));
        } catch (Exception e) {
            throw new ServerInternalErrorException("can't read request");
        }
       final int status = 201;
        return ResponseEntity.of("created", status);
    }

    private ResponseEntity handeGET(HttpExchange exchange) {
        final int status = 200;
        return ResponseEntity.of("deleted", status);
    }

    private static void sendResponse(HttpExchange exchange, ResponseEntity responseEntity) throws IOException {
        exchange.sendResponseHeaders(responseEntity.getStatus(), responseEntity.getContent().length());
        OutputStream os = exchange.getResponseBody();
        os.write(responseEntity.getContent().getBytes());
        os.close();
    }
}
