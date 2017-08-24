package ua.artem.golovchenko.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheServer;
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
    private static WebServerApi webApiServer;
    private static ua.golovchenko.artem.game.cache.CacheServer cacheServer;

    public static void init() throws Exception{
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

}
