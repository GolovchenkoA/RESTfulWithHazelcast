package ua.golovchenko.artem.game.web.server;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.config.Config;
import ua.golovchenko.artem.game.web.controller.UserHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by головченко on 03.08.2017.
 *
 * @author Golovchenko Artem
 */
public class WebServerApi {
    private static final Logger logger = LoggerFactory.getLogger(WebServerApi.class);
    public static final String PORT_PARAM = "web_api_server.port";
    public static String port;

    HttpServer server;

    public WebServerApi(Config config) throws IOException {
        port = config.getString(PORT_PARAM,"80");
        logger.info("Start configure web API server on port: {}",port );
        server = HttpServer.create(new InetSocketAddress(Integer.parseInt(port)),0);
        server.createContext("/useringo", new UserHandler());
    }

    public void start() {
        try {
            logger.info("Start web API server on port: {}",port );
            server.start();
        } catch (Exception e) {
            logger.info("Failed start web API server. StackTrace: {}",e);
        }
    }

}
