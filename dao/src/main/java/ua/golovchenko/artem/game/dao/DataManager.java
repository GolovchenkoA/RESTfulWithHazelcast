package ua.golovchenko.artem.game.dao;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheServer;
import ua.golovchenko.artem.game.cache.dao.CacheClient;

/**
 * Created by Artem on 12.08.2017.
 * Providing connecting to cache server
 */


public class DataManager {
    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);
    HazelcastInstance instance = null;
    CacheClient cacheClient = null;

    /**
     * If local cache-server enabled - use local server.
     * If local cache-server not enabled - use cache-client for connecting to remote cache server
     */
    private DataManager(){
        logger.debug("Class constructor");

        if(Boolean.parseBoolean(System.getProperty("cache"))){
            logger.info("Local cache server enabled. Cache client does not used");
            instance = CacheServer.getInstance();

        }else {
            try {
                logger.info("Local cache server does not enabled. Cache client used");
                cacheClient = new CacheClient();
                instance = cacheClient.getCache();
            } catch (Exception e) {
                logger.info("Class initialization error. Can not get cache client");
                e.printStackTrace();
            }

        }
    }

    public static class SingltonDataManager{
        public static final DataManager CLASS_INSTANCE = new DataManager();
    }


    public static DataManager getInstance(){
        logger.info("get DataManager.class instance");
        return SingltonDataManager.CLASS_INSTANCE;
    }

    /**
     *
     * @return HazelcastInstance Can be NULL if can not get cache client
     */

    public HazelcastInstance getCache(){
        return instance;
    }

}
