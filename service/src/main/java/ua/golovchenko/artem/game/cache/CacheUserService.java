package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.dao.BaseUserDAO;
import ua.golovchenko.artem.game.dao.UserDAO;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.util.List;
import java.util.Map;

/**
 * Created by Artem on 20.08.2017.
 *
 * @author Artem Golovchenko
 */
public class CacheUserService implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int TOP_COUNT = 40;
    private UserDAO userDAO = new BaseUserDAO();

/*    public CacheUserService(){
        userDAO = DataManager.getUserDAO();
    }*/

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public void add(User user) throws Exception {
     try{
        userDAO.add(user);
        logger.debug("User creatyed: {}",user);
    } catch (Exception e) {
        logger.debug("Error create user. StackTrace {}", e);
        throw new Exception(e);
    }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Map<Long, User> findAll() throws Exception {
        return userDAO.findAll();
    }

    @Override
    public List<Result> getTop(Long id, Long topCount) throws Exception {
        User user = userDAO.get(id);
        user.getResults().sort((r1, r2) -> r2.getResult().compareTo(r1.getResult()));
        List<Result> results = user.getResults();
        logger.debug("All results before getTop {} results. UserId: {}, allResults: {}",TOP_COUNT, id, results);
        int end = (results.size() < TOP_COUNT) ? results.size() : TOP_COUNT;
        List<Result> resultList = user.getResults().subList(0,end);

        return resultList;
    }

    @Override
    public void update(User user) throws Exception {
        try {
            userDAO.update(user);
            logger.debug("User updated: {}",user);
        } catch (Exception e) {
            logger.debug("Error update user. StackTrace {}", e);
            throw new Exception(e);
        }
    }

    @Override
    public User generateNewUser(Long id) {
        String email = (new StringBuilder("email" + id + "@auto.generate")).toString();
        String name = (new StringBuilder("name_" + id + "auto.generate")).toString();
        String nick = (new StringBuilder("nick_" + id + "auto.generate")).toString();
        User user = new UserBase(email,name,nick);
        user.setUser_id(id);
        return user;
    }
}
