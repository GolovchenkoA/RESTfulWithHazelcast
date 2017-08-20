package ua.golovchenko.artem.game.cache;

import ua.golovchenko.artem.game.dao.BaseUserDAO;
import ua.golovchenko.artem.game.dao.UserDAO;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.User;

import java.util.Map;

/**
 * Created by Artem on 20.08.2017.
 *
 * @author Artem Golovchenko
 */
public class CacheUserService implements UserService {
    private UserDAO userDAO = new BaseUserDAO();

/*    public CacheUserService(){
        userDAO = DataManager.getUserDAO();
    }*/

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public void add(User obj) throws Exception {
        userDAO.add(obj);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Map<Long, User> findAll() throws Exception {
        return userDAO.findAll();
    }
}
