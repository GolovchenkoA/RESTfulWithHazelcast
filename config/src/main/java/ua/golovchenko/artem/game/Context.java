package ua.golovchenko.artem.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.cache.CacheServer;
import ua.golovchenko.artem.game.config.Config;
import ua.golovchenko.artem.web.WebServerApi;

/**
 * Created by Artem on 07.08.2017.
 *
 * @author Golovchenko Artem
 * Application initialization class
 *
 */
public final class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);

    private static Config config;
    private static CacheServer cacheServer;
    private static WebServerApi webApiServer;
    private static final String CACHE_SERVER = "cache_server.enable";
    private static final String WEB_API_SERVER = "web_api_server.enable";
    private static DataManager dataManager;

    private Context() {
    }

    public static Config getConfig() {
        return config;
    }

    public static void init(String file) throws Exception {
        config = new Config();
        config.load(file);

        logger.debug("Create DataManager");
        dataManager = new DataManager();

        // Cache Server Configuration
        if (config.hasKey(CACHE_SERVER) && config.getBoolean(CACHE_SERVER)) {
            logger.info("Config. Cache server: enabled");
            cacheServer = new CacheServer();
        }else {
            logger.info("Config. Cache server disabled");
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

    public static WebServerApi getWebApiServer() {
        return webApiServer;
    }
    public static CacheServer getCacheServer() {
        return cacheServer;
    }
    public static DataManager getDataManager() {
        return dataManager;
    }
}
