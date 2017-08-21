package ua.golovchenko.artem.game.cache.dao;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.dao.InfoDAO;
import ua.golovchenko.artem.model.Info;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public class CacheInfoDAO implements InfoDAO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final DataManager dataManager = new DataManager();


    public CacheInfoDAO() {
        logger.debug("Class constructor");
        logger.debug("load DataManager");

    }

    @Override
    public void add(Info item) throws Exception {
        getCache().getMultiMap("info_by_level").put(item.getLevel_id(), item);
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
