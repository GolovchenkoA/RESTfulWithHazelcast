package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.dao.CacheResultDAO;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.dao.ResultDAO;
import ua.golovchenko.artem.game.service.ResultService;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.User;

import java.util.Collections;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public class CacheResultService implements ResultService {
    private static final int MAX_RESULTS_COUNT = 3;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    DataManager dataManager = new DataManager();
    ResultDAO resultDAO = new CacheResultDAO();
    CacheUserService userService = new CacheUserService();
    CacheLevelService levelService = new CacheLevelService();


    @Override
    public void add(Result result) throws Exception {
        Long user_id = result.getUser_id();
        User user = userService.get(user_id);
        if(user == null){
            logger.debug("User with id [{}] does not exists. Create new user ", user_id);
            user = userService.generateNewUser(user_id);
            userService.add(user);
        }


        int items_cont;
        try{
            items_cont = user.getResults().size();
        }catch (Exception e){
            logger.debug("User [id: {}] have no results.",user_id);
            items_cont = 0;
        }

        if(items_cont >= MAX_RESULTS_COUNT){
            Collections.sort(user.getResults());
            logger.debug("User with id [{}] have maximum results count [{}]. removing item", user_id,MAX_RESULTS_COUNT);
            user.getResults().subList(MAX_RESULTS_COUNT, items_cont).clear();
        }

        logger.debug("Add new result [{}] to user with id [{}] .start ", user_id, result);
        user.getResults().add(result);


        try {
            userService.update(user);
            logger.debug("UserService. Add new result [{}] to user with id [{}] . finish", user_id, result);
        } catch (Exception e) {
            logger.debug("Error update user. StackTrace {}", e);
            throw new Exception(e);
        }

        //Update Results in Level-MultiMap
        try{
            levelService.update(user);
            logger.debug("LevelService. Add new result [{}] to user with id [{}] . finish", user_id, result);
        } catch (Exception e) {
            logger.debug("Error update user. StackTrace {}", e);
            throw new Exception(e);
        }

    }
}
