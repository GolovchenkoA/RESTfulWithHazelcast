package ua.golovchenko.artem.game.web;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.config.Config;
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
    private static final String PORT_PARAM = "web_api_server.port";
    private static final String DEFAULT_PORT = "80";
    private static String port;
    private Config config;

    private HttpServer server;

    public WebServerApi() throws IOException {
        config = new Config();
        config.setKeyValue(PORT_PARAM, DEFAULT_PORT);
        this.init(config);
    }

    public WebServerApi(Config config) throws IOException {
        this.config = config;
        this.init(config);
    }

    private void init(Config config) throws IOException {
        port = config.getString(PORT_PARAM, DEFAULT_PORT);
        logger.info("Start configure web API server on port: {}", port);
        server = HttpServer.create(new InetSocketAddress(Integer.parseInt(port)), 0);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JaxRsApplication(), HttpHandler.class);
        server.createContext("/", handler);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("Shutdown web API server on port: {}", port);
                server.stop(0);
            }
        }));
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
