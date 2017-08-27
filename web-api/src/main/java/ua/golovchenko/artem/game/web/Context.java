package ua.golovchenko.artem.game.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheServer;

import java.io.IOException;

/**
 * Created by Artem on 12.08.2017.
 * Cache-server context
 * @author Golovchenko Artem
 */
public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    private static WebServerApi webApiServer;
    private static CacheServer CacheServer;

    private Context() {
    }

    public static void init() throws IOException {
        // Web API Server Configuration
            logger.info("Config. Web API server: enabled");
            webApiServer = new WebServerApi();
            webApiServer.start();

    }
}
