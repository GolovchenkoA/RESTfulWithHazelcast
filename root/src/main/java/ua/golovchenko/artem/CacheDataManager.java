package ua.golovchenko.artem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.web.CacheClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Artem on 11.08.2017.
 *
 * @author Golovchenko Artem
 *
 * Class for working with the caching system
 */
public class CacheDataManager {
    private static final Logger logger = LoggerFactory.getLogger(CacheDataManager.class);
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private static CacheClient cacheClient;

    public CacheDataManager() {
        logger.debug("Class constructor");
    }

    public static CacheClient getCacheClient(){
        logger.debug("Load cacheClient");

        try {
            cacheClient = new CacheClient();
        } catch (Exception e) {
            logger.info("Failed load cache client. StackTrace: {}", e);
        }
        return cacheClient;
    }

}
