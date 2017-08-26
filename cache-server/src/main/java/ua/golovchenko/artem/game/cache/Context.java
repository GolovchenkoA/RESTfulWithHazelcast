package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Artem on 12.08.2017.
 */
public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    private static CacheServer cacheServer;
    private static final String CACHE_SERVER = "cache";

    public static void init() throws IOException {

        //Cache Server config
        if(Boolean.parseBoolean(System.getProperty((CACHE_SERVER)))){
            logger.info("Config. Cache server: enabled");
            cacheServer = new CacheServer();
        }else {
            logger.info("Config. Cache server disabled");
        }
    }
}
