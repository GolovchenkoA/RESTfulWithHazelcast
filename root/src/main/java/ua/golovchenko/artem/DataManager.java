package ua.golovchenko.artem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 10.08.2017.
 *
 *@author Golovchenko Artem
 * Class for working with object storage systems
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
}
