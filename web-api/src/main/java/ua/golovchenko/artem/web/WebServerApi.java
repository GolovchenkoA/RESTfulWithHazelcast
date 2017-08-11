package ua.golovchenko.artem.web;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.config.Config;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by головченко on 03.08.2017.
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
        server.createContext("/", new BaseHandler());
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


    /**
     *
     * @param i the maximum time in seconds to wait until exchanges have finished.
     * @see com.sun.net.httpserver.HttpServer
     */
    public void stop(int i) {
        try {
            server.stop(i);
            logger.info("Web API server stopped. port: {}",port );
        } catch (Exception e) {
            logger.info("Failed start web API server. StackTrace: {}",e);
        }
    }

}
