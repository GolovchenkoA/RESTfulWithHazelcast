package ua.golovchenko.artem.web;

import com.sun.net.httpserver.HttpServer;
import ua.golovchenko.artem.game.config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by головченко on 03.08.2017.
 */
public class Web {
    public static final int PORT = 80;

    public Web(Config config) {

    }


    public static void main(String[] args) throws IOException {
/*        HttpServer server = HttpServer.create(new InetSocketAddress(PORT));
        server.createContext("/", new BaseHandler());
        server.start();*/

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
        server.createContext("/", new BaseHandler());
        server.createContext("/useringo", new UserHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

/*    static class BaseHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response 2";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }*/

}
