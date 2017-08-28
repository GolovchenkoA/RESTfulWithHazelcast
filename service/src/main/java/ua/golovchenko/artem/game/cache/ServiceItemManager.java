package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 28.08.2017.
 *
 * @author Artem Golovchenko
 */
public class ServiceItemManager {


    private static Logger logger = LoggerFactory.getLogger(ServiceItemManager.class);
    private static final int MAX_RESULTS_COUNT_DEFAULT = 20;
    private static int MAX_RESULTS_COUNT;
    private static final int TOP_USERS_COUNT_DEFAULT = 20;
    private static int TOP_USERS_COUNT;


    private ServiceItemManager(){}

    private static class SingltonServiceItemManager{
        public static final ServiceItemManager CLASS_INSTANCE = new ServiceItemManager();
    }

    public static ServiceItemManager getInstance(){
        logger.info("get DataManager.class instance");
        return SingltonServiceItemManager.CLASS_INSTANCE;
    }

    public void init(){
        this.setTopLevelCount();
        this.setTopUsersCount();
    }

    private void setTopLevelCount() {

        try{
            int i = Integer.parseInt(System.getProperty("itemcount"));
            MAX_RESULTS_COUNT = i >=1 ? i : MAX_RESULTS_COUNT_DEFAULT;
            logger.info("Maximum results count per user : {}",MAX_RESULTS_COUNT);
        } catch (Exception e){
            logger.info("Maximum results count per user is not specified or incorrect. Default: {}",MAX_RESULTS_COUNT_DEFAULT);
            MAX_RESULTS_COUNT = MAX_RESULTS_COUNT_DEFAULT;
        }

    }

    private void setTopUsersCount() {

        try{
            int i = Integer.parseInt(System.getProperty("itemcount"));
            TOP_USERS_COUNT = i >=1 ? i : TOP_USERS_COUNT_DEFAULT;
            logger.info("Top results count per user on level: {}", TOP_USERS_COUNT);
        } catch (Exception e){
            logger.info("Top results count per user on level is not specified or incorrect. Default: {}", TOP_USERS_COUNT_DEFAULT);
            TOP_USERS_COUNT = TOP_USERS_COUNT_DEFAULT;
        }

    }

    public int getTopUserCount(){
        return TOP_USERS_COUNT;
    }

    public int getMaxResultsCount(){
        return MAX_RESULTS_COUNT;
    }

}
