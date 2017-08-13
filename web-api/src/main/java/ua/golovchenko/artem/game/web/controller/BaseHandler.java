package ua.golovchenko.artem.game.web.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Artem on 12.08.2017.
 */
public class BaseHandler implements HttpHandler {

    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        is.read(); // .. read the request body
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
