package ua.golovchenko.artem.game.dao;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.dao.CacheClient;

/**
 * Created by Artem on 12.08.2017.
 */
public class DataManager {
    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

    public DataManager(){
        logger.info("Class constructor");
    }

    public HazelcastInstance getCache() throws Exception {
        CacheClient cache = new CacheClient();

        return cache.getCache();
    }
}
