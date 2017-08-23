package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.dao.CacheResultDAO;
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
    private static final int MAX_RESULTS_COUNT = 20;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    ResultDAO resultDAO = new CacheResultDAO();
    CacheUserService userService = new CacheUserService();
    CacheLevelService levelService = new CacheLevelService();


    @Override
    public void add(Result result) throws Exception {

/*        Old variant
        try{
            resultDAO.add(item);
        } catch (Exception e) {
            logger.info("Error add new Info item: {}", e);
            throw new Exception("Error add new Info item");

        }*/

        //Update User in User-MAP

        User user = userService.get(result.getUser_id());
        int items_cont = user.getResults().size();

        if(items_cont >= MAX_RESULTS_COUNT){
            Collections.sort(user.getResults());
            logger.debug("User with id [{}] have maximum results count [{}]. removing item", result.getUser_id(),MAX_RESULTS_COUNT);
            user.getResults().subList(MAX_RESULTS_COUNT, items_cont).clear();
        }

        logger.debug("Add new result [{}] to user with id [{}] .start ", result.getUser_id(), result);
        user.getResults().add(result);

        try {
            userService.update(user);
            logger.debug("Add new result [{}] to user with id [{}] . finish", result.getUser_id(), result);
        } catch (Exception e) {
            logger.debug("Error update user. StackTrace {}", e);
            throw new Exception(e);
        }


        //Update Results in Level-MultiMap

        levelService.update(user);


    }
}
