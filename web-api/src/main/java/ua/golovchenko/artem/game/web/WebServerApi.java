package ua.golovchenko.artem.game.web;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.web.controller.jaxrs.JaxRsApplication;

import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by головченко on 03.08.2017.
 *
 * @author Golovchenko Artem
 */
public class WebServerApi {
    private static final Logger logger = LoggerFactory.getLogger(WebServerApi.class);
    private static int DEFAULT_WEB_PORT = 80;
    private static int port;

    private HttpServer server;

    public WebServerApi() throws IOException {
        port = Boolean.parseBoolean(System.getProperty("webport")) ? Integer.parseInt(System.getProperty("webport")) : DEFAULT_WEB_PORT;
        logger.info("Start configure web API server on port: {}", port);
        server = HttpServer.create(new InetSocketAddress(port), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JaxRsApplication(), HttpHandler.class);
        server.createContext("/", handler);

    }

    public void start() {
        try {
            logger.info("Start web API server on port: {}",port );
            server.start();
        } catch (Exception e) {
            logger.info("Failed start web API server. StackTrace: {}",e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("Shutdown web API server on port: {}", port);
                server.stop(0);
            }
        }));
    }

}
