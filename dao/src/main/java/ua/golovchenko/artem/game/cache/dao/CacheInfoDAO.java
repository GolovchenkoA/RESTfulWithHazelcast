package ua.golovchenko.artem.game.cache.dao;

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


    @Override
    public void add(Info item) throws Exception {
        dataManager.getCache().getMap("info");
    }
}
