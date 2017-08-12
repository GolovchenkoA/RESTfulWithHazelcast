package ua.golovchenko.artem.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.CacheDataManager;

import java.util.Collection;

/**
 * Created by Artem on 10.08.2017.
 */
public class DataManager {
    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);
    private static CacheDataManager cacheDataManager;

    public DataManager(){
        logger.debug("Class constructor");
        logger.debug("Load CacheDataManager");
        cacheDataManager = new CacheDataManager();
    }

    public CacheDataManager getCacheDataManager() {
        return cacheDataManager;
    }

/*    public Collection<Object> getStorage() {
        return storage;
    }*/
}
