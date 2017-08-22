package ua.artem.golovchenko.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheServer;
import ua.golovchenko.artem.game.config.Config;
import ua.golovchenko.artem.game.web.WebServerApi;

import java.io.IOException;

/**
 * Created by Artem on 20.08.2017.
 *
 *
 * List of params
 *
 *
 *
 *
 * @author Artem Golovchenko
 */
public class RootContext {
    private static final Logger logger = LoggerFactory.getLogger(RootContext.class);
    private static final String WEB_SERVER = "web";
    private static final String CACHE_SERVER = "cache";
    private static final String WEB_CONFIG = "webconf";
    private static final String CACHE_CONFIG = "cahceconf";
    private static WebServerApi webApiServer;
    private static ua.golovchenko.artem.game.cache.CacheServer cacheServer;
    private static Config config;

    //public static void init(String[] arguments) throws Exception{
    public static void init() throws Exception{

/*

        config = new Config();
        if (arguments.length <= 0) {
            throw new RuntimeException("Configuration file is not provided");
        }

        config.load(arguments[0]);
        System.out.printf("Config: %s", config.toString());
*/
        initServices();
    }



    private static void initServices() throws IOException {

        //Cache Server config
        if(Boolean.parseBoolean(System.getProperty((CACHE_SERVER)))){
            logger.info("Config. Cache server: enabled");
            cacheServer = new CacheServer();
        }else {
            logger.info("Config. Cache server disabled");
        }

        //Web Server config
        if(Boolean.parseBoolean(System.getProperty((WEB_SERVER)))){

            logger.info("Config. Web API server: enabled");
            webApiServer = new WebServerApi();
            webApiServer.start();
        }else {
            logger.info("Config. Web API server disabled");
        }

    }

/*
    private static void initServices() {
        if(config.hasKey(WEB_SERVER)){

            logger.info("Config. Web API server: enabled");
            webApiServer = settings.getTop(WEB_CONFIG) ? new WebServerApi(WEB_CONFIG) : new WebServerApi();
            webApiServer.start();
        }else {
            logger.info("Config. Web API server disabled");
        }


        if(config(CACHE_SERVER)){

        }

    }

*/

}
