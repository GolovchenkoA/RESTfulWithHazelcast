package ua.golovchenko.artem.web;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by головченко on 03.08.2017.
 */
public class WebServerApi {
    private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);
    public static final int PORT = 80;

    public static void main(String[] args) throws IOException {
/*        HttpServer server = HttpServer.create(new InetSocketAddress(PORT));
        server.createContext("/", new BaseHandler());
        server.start();*/


        new WebServerApi().start();


    }

    private void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);
        server.createContext("/", new BaseHandler());
        server.createContext("/useringo", new UserHandler());
        server.setExecutor(null); // creates a default executor

        if(server != null){
            server.start();
            logger.info("Web-API Server started on port: {}", PORT);
        }else{
            logger.info("Failed to start server on port: {}", PORT);
        }






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
