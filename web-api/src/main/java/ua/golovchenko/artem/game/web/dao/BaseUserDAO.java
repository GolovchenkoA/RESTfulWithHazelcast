package ua.golovchenko.artem.game.web.dao;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.web.Context;
import ua.golovchenko.artem.game.web.DataManager;
import ua.golovchenko.artem.model.User;
import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 *
 * @author Golovchenko Artem
 * The main class for working with objects of the type User
 */
public class BaseUserDAO implements UsersManager<User> {
    private static final Logger logger = LoggerFactory.getLogger(StringUserDAO.class);
    private DataManager dataManager; // = Context.getDataManager();
    //private static Map<Long, User> users = new HashMap<>();


    public BaseUserDAO() {
/*        User user = new UserBase("email@com.com","user10","nick10");
        user.setId(10L);
        users.put(user.getId(), user);*/
        logger.debug("load DataManager");
        dataManager = Context.getDataManager();
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public void addUser(User user) throws Exception {
        //users.put(user.getId(), user);

        getCache().getMap("users").put(user.getId(), user);
        logger.debug("added User {}",user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public boolean updateUser(Long id) {
        return false;
    }

    @Override
    public Map<Long,User> findAll() throws Exception {
        return getCache().getMap("users");
    }

    private HazelcastInstance getCache() throws Exception {
        try {
            return dataManager.getCache();
        } catch (Exception e) {
            logger.debug("Error getting the cache-client. StackTrace {}",e);
            throw new Exception(e);
        }
    }
}
