package ua.golovchenko.artem.game.cache.dao;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.dao.UserDAO;
import ua.golovchenko.artem.model.User;

import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 *
 * @author Golovchenko Artem
 * The main class for working with objects of the type User
 */
public class BaseUserDAO implements UserDAO {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String USERS_MAP = "users";
    private static final DataManager dataManager = new DataManager();
    //private DataManager dataManager = Context.getDataManager();
    //private static Map<Long, User> users = new HashMap<>();


    public BaseUserDAO() {
/*        User user = new UserBase("email@com.com","user10","nick10");
        user.setUser_id(10L);
        users.put(user.getUser_id(), user);*/
        logger.debug("Class constructor");
        logger.debug("load DataManager");
        //dataManager = new DataManager();

    }

    @Override
    public User get(Long id) throws Exception {
        return this.findAll().get(id);
    }

    @Override
    public void add(User user) throws Exception {
        //users.put(user.getUser_id(), user);

        getCache().getMap("users").put(user.getUser_id(), user);
        logger.debug("added User {}",user);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Map<Long,User> findAll() throws Exception {
        logger.debug("findAll() . result: {}", getCache().getMap("users"));
        return getCache().getMap(USERS_MAP);
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
