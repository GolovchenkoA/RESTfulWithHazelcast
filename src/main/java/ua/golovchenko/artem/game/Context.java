package ua.golovchenko.artem.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Cache;
import ua.golovchenko.artem.cache.CacheServer;
import ua.golovchenko.artem.game.config.Config;
import ua.golovchenko.artem.web.Web;

import java.io.IOException;

/**
 * Created by Artem on 07.08.2017.
 */
public final class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);

    private static Config config;
    private static CacheServer cacheServer;
    private static Web webApiServer;
    private static DataManager dataManager;

    private static final String CACHE_SERVER = "cache_server.enable";
    private static final String CACHE_SERVER_CONFIG_FILE = "cache_server.configFile";
    private static final String WEB_API_SERVER = "web_api_server.enable";
    //private static final String WEB_API_SERVER_CONFIG_FILE = "web_api_server.configFile";

    private Context() {
    }

    public static Config getConfig() {
        return config;
    }

    public static void init(String file) throws Exception {
        config = new Config();
        config.load(file);


        // Cache Server Configuration
        if (config.hasKey(CACHE_SERVER) && config.getBoolean(CACHE_SERVER)) {
            logger.info("Config. Cache server: enabled");
            String conifigFile = config.getString(CACHE_SERVER_CONFIG_FILE);
            config = new Config();

            try {
                config.load(conifigFile);
                cacheServer = new CacheServer(config);
            } catch (IOException e) {
                logger.info("Failed load cache server config file [ {} ]. StackTrace: {}",CACHE_SERVER_CONFIG_FILE,e);
            }
        }else {
            logger.info("Config. Cache server disabled");
        }

        // Web API Server Configuration
        if (config.hasKey(WEB_API_SERVER) && config.getBoolean(WEB_API_SERVER)) {
            logger.info("Config. Web API server: enabled");
/*            String conifigFile = config.getString(WEB_API_SERVER_CONFIG_FILE);
            config = new Config();*/

            try {
                //config.load(conifigFile);
                webApiServer = new Web(config);
            } catch (IOException e) {
                //logger.info("Failed load web API server config file [ {} ]. StackTrace: {}",WEB_API_SERVER_CONFIG_FILE ,e);
                logger.info("Failed load web API server. StackTrace: {}",e);
            }
        }else {
            logger.info("Config. Web API server disabled");
        }

    }

    public static DataManager getDataManager() {
        return dataManager;
    }
    public static Web getWebApiServer() {
        return webApiServer;
    }
    public static CacheServer getCacheServer() {
        return cacheServer;
    }
}
