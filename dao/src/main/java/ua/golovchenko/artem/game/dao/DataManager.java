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

    private DataManager(){}

    public static class SingltonDataManager{
        public static final DataManager CLASS_INSTANCE = new DataManager();
    }

    public static DataManager getInstance(){
        logger.info("get DataManager.class instance");
        return SingltonDataManager.CLASS_INSTANCE;
    }

    public HazelcastInstance getCache() throws Exception {
        CacheClient cache = new CacheClient();

        return cache.getCache();
    }
}
