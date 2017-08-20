package ua.golovchenko.artem.game.dao;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 12.08.2017.
 */
public class DataManager {
    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

    public DataManager(){
        logger.info("Class constructor");
    }
/*    private static DataManager dataManager;
    private static UserDAO userDAO;*/


/*    private DataManager(Boolean cacheEnable, Boolean webEnable){
        if(dataManager == null){
            logger.info("Class constructor. Cache server enable: {} , Web server enable: {}", cacheEnable, webEnable);
            dataManager = new DataManager(cacheEnable, webEnable);

            if(webEnable){
                userDAO = new
            }

        }

    }*/

/*    public static void init(Boolean cacheEnable, Boolean webEnable){
        dataManager = new DataManager(cacheEnable,webEnable);
    }


    public static UserDAO getUserDAO() {
        if( userDAO != null){
            return userDAO;
        }else {
            throw new NullPointerException("UserDAO not initialized");
        }

    }*/


    public HazelcastInstance getCache() throws Exception {
        CacheClient cache = new CacheClient();

        return cache.getCache();
    }
}
