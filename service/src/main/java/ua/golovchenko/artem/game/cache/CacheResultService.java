package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CacheUserService userService;
    private CacheLevelService levelService;

    public CacheResultService() {
        this.userService  = new CacheUserService();
        this.levelService = new CacheLevelService();
    }

    public CacheResultService(CacheUserService userService, CacheLevelService levelService) {
        this.userService = userService;
        this.levelService = levelService;
    }

    /**
     * Set maximum results count per user
     */

    @Override
    public void add(Result result) throws Exception {
        Long user_id =  result.getUser_id();
        User user =  userService.get(user_id);

        user = this.createUserIfNotExists(user,user_id);

        this.checkMaximumNumberOfResultsAllowed(user, ServiceItemManager.getInstance().getMaxResultsCount());
        this.updateCacheCollections(user,result);
    }

    /**
     *
     * @param user owner of results
     * @param maximum_number stored results item
     * @return int deleted items count
     */
    int checkMaximumNumberOfResultsAllowed(User user, int maximum_number) {
        int items_count;
        int removedItemCount = 0;

        try{
            items_count = user.getResults().size();
        }catch (Exception e){
            logger.debug("User [id: {}] have no results.",user.getUser_id());
            items_count = 0;
        }

        if(items_count >= maximum_number){
            Collections.sort(user.getResults());
            logger.debug("User with id [{}] have maximum results count [{}]. removing item", user.getUser_id(), maximum_number);
            removedItemCount = user.getResults().subList(maximum_number, items_count).size();
            user.getResults().subList(maximum_number, items_count).clear();
        }

        logger.debug("Removed {} results. User id: {} ", removedItemCount, user.getUser_id());
        return removedItemCount;
    }

    User createUserIfNotExists(User user, Long user_id) throws Exception {
        if(user == null){
            logger.debug("User with id [{}] does not exists. Create new user ", user_id);
            user = userService.generateNewUser(user_id);
            userService.add(user);
        }
        return user;
    }

    void updateCacheCollections(User user, Result result) throws Exception {
        logger.debug("Add new result [{}] to user with id [{}] .start ", user.getUser_id(), result);
        user.getResults().add(result);

        //Update Results in UsersMap
            try {
                userService.update(user);
                logger.debug("UserService. Add new result [{}] to user with id [{}] . finish", result, user.getUser_id());
            } catch (Exception e) {
                logger.debug("Error update user. StackTrace {}", e);
                throw new Exception(e);
            }


        //Update Results in Level-MultiMap
        try{
            levelService.update(user);
            logger.debug("LevelService. Add new result [{}] to user with id [{}] . finish", user.getUser_id(), result);
        } catch (Exception e) {
            logger.debug("Error update user. StackTrace {}", e);
            throw new Exception(e);
        }
    }

    CacheUserService getUserService() {
        return userService;
    }

    CacheLevelService getLevelService() {
        return levelService;
    }
}
