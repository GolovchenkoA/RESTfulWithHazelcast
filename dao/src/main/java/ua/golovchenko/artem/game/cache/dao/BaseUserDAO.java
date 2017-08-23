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


    public BaseUserDAO() {
        logger.debug("Class constructor");
        logger.debug("load DataManager");
    }

    @Override
    public User get(Long id) throws Exception {
        return this.findAll().get(id);
    }

    @Override
    public void add(User user) throws Exception {
        try {
            getCache().getMap(USERS_MAP).put(user.getUser_id(), user);
            logger.debug("added User {}",user);
        } catch (Exception e) {
            logger.debug("Error update user. StackTrace {}",e);
            throw new Exception(e);
        }
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

    @Override
    public void update(User user) throws Exception  {
        try {
            getCache().getMap(USERS_MAP).put(user.getUser_id(), user);
            logger.debug("User updated: {}",user);
        } catch (Exception e) {
            logger.debug("Error update user. StackTrace {}",e);
            throw new Exception(e);
        }
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
