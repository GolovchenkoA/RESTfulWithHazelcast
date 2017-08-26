package ua.golovchenko.artem.game.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheServer;
import ua.golovchenko.artem.game.config.Config;

import java.io.IOException;

/**
 * Created by Artem on 12.08.2017.
 * Cache-server context
 * @author Golovchenko Artem
 */
public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    private static final String WEB_API_SERVER = "web_api_server.enable";
    private static final String CACHE_SERVER = "cache_server.enable";
    private static WebServerApi webApiServer;
    private static CacheServer CacheServer;
    private static Config config;

    private Context() {
    }

    public static Config getConfig() {
        return config;
    }

    public static void init(String file) throws IOException {

        try {
            config = new Config();
            config.load(file);

        } catch (IOException e) {
            logger.info("Error load configuration file: {}", file);
            throw new IOException(e);
        }

        // Web API Server Configuration
        if (config.hasKey(WEB_API_SERVER) && config.getBoolean(WEB_API_SERVER)) {
            logger.info("Config. Web API server: enabled");
            webApiServer = new WebServerApi(config);
            webApiServer.start();
        }else {
            logger.info("Config. Web API server disabled");
        }
    }
}
