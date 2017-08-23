package ua.golovchenko.artem.game.cache.dao;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.dao.ResultDAO;
import ua.golovchenko.artem.model.Result;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public class CacheResultDAO implements ResultDAO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String INFO_LEVEL_MAP = "info_by_level";
    private static final String INFO_USER_MAP = "info_by_user" ;
    private static final DataManager dataManager = new DataManager();


    public CacheResultDAO() {
        logger.debug("Class constructor");
        logger.debug("load DataManager");
    }

    @Override
    public void add(Result item) throws Exception {
        getCache().getMultiMap(INFO_LEVEL_MAP).put(item.getLevel_id(), item);
        getCache().getMultiMap(INFO_USER_MAP).put(item.getUser_id(), item);
    }

    private HazelcastInstance getCache() throws Exception {
        try {
            return dataManager.getCache();
        } catch (Exception e) {
            logger.debug("Connection to cache server failed. StackTrace {}",e);
            throw new Exception(e);
        }
    }
}
