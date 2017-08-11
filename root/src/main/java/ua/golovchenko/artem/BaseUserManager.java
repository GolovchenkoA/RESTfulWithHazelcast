package ua.golovchenko.artem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.DataManager;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.game.Context;
import ua.golovchenko.artem.model.UserBase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 */
public class BaseUserManager implements UsersManager<User> {
    private static final Logger logger = LoggerFactory.getLogger(StringUserManager.class);
    private static DataManager dataManager = Context.getDataManager();
    private static Map<Long, User> users = new HashMap<>();


    public BaseUserManager() {
        User user = new UserBase("email@com.com","user10","nick10");
        user.setId(10L);
        users.put(user.getId(), user);
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
        logger.debug("add User {}, all users: {}",user);
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
    public Map<Long,User> findAll() {
        return users;
    }
}
