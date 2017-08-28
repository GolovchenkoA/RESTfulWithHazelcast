package ua.golovchenko.artem.game.cache;


import com.hazelcast.core.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.service.LevelService;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */

public class CacheLevelService implements LevelService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String USERS_MAP = "users";
    private static final String RESULTS_LEVEL_MAP = "info_by_level";
    private static final int TOP_COUNT_DEFAULT = 20;
    private static int TOP_COUNT;
    private UserService userService;


    public CacheLevelService(){

        this.userService = new CacheUserService();
        this.setTopCount();
    }


    public CacheLevelService(UserService userService){
        this.userService = userService;
        this.setTopCount();
    }

    /**
     *
     * @param level Level number
     * @param topCount Maximum number of users
     * @return Top users with results on this level
     * @throws Exception
     */

    @Override
    public List<User> getTop(Integer level, Integer topCount) throws Exception {
        logger.debug("Generate report of top users result on level {}.", level);
        MultiMap<Integer, Result> map = DataManager.getInstance().getCache().getMultiMap(RESULTS_LEVEL_MAP);
        logger.debug("Get top users on level {}. All users: {}", level, map);
        Collection<Result> allResultsOnLevel = map.get(level);
        List<Result> allResultsOnLevelModifiedList = new ArrayList<>(allResultsOnLevel);
        logger.debug(" Unsorted List:\n {}", allResultsOnLevelModifiedList);

        // 1. Сортировать результаты на уровне по убыванию
        logger.debug("1. Sort results on level by descending");
        Collections.sort((List) allResultsOnLevelModifiedList);
        logger.debug("Sorted list:\n {}", allResultsOnLevelModifiedList);

        //1.1  и оставить уникальные результаты (по признаку id-пользователя)
        logger.debug("1.1  и оставить уникальные результаты (по признаку id-пользователя)");
        List<Result> uniqueAllResultsByUser = allResultsOnLevelModifiedList.stream().filter(distinctByKey(o -> o.getUser_id())).collect(Collectors.toList());
        logger.debug("Unique results on level:\n {}", uniqueAllResultsByUser);

        // 2. Надо выбрать топ уникальных пользователей
        logger.debug("2. Top unique users");
        int end = (uniqueAllResultsByUser.size() < TOP_COUNT) ? uniqueAllResultsByUser.size() : TOP_COUNT;
        List<Result>  uniqueTopResultsByUser = uniqueAllResultsByUser.subList(0,end);
        logger.debug("Top unique users:\n {}",uniqueTopResultsByUser);

        //3. Получаем список ID пользователей
        logger.debug("3. Getting list users ids");
        List<Long> usersIds = uniqueTopResultsByUser.stream().map(Result::getUser_id).collect(Collectors.toList());
        logger.debug("3.TOP [{}] users ids : {}",TOP_COUNT, usersIds);


        //4.!!!!!!!!!!! Надо вернуть пользователей (с результатами) в порядке убывания
        List<User> allResultsOfTopUsersOnLevel = new ArrayList<>();

        for(int u = 0; u < usersIds.size(); u++) {
            final int userId = u;
            allResultsOfTopUsersOnLevel.addAll(userService.findAll().values().stream().filter(user -> user.getUser_id().equals(usersIds.get(userId))).collect(Collectors.toList()));
        }

        //5. Сортируем пользователей.;
        logger.debug("4. Sorting users. By Descending (On top, users with the highest score)");
        this.sortInDescending(allResultsOfTopUsersOnLevel);

        return allResultsOfTopUsersOnLevel;
    }


    void sortInDescending(List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return ((u2.getResults().stream().mapToInt(res -> res.getResult()).sum()) -
                        (u1.getResults().stream().mapToInt(res -> res.getResult()).sum()));
            }
        });
    }

    @Override
    public void update(User user) throws Exception {
        Long userId = user.getUser_id();
        logger.debug("Updating user with id: [{}].  results in maps: {},", userId, RESULTS_LEVEL_MAP,USERS_MAP);

        //Update results in users map
        ConcurrentMap<Long, User> usersMap = DataManager.getInstance().getCache().getMap(USERS_MAP);
        User updatedUser = usersMap.get(userId);
        updatedUser.setResults(user.getResults());
        usersMap.put(userId,updatedUser);

        //Update results on levels
        MultiMap<Integer, Result> resultsLevelMap = DataManager.getInstance().getCache().getMultiMap(RESULTS_LEVEL_MAP);
        logger.debug("Results in map: {} \n before update user [{}] results : {}",RESULTS_LEVEL_MAP, user, resultsLevelMap.values() );
        user.getResults().forEach(res -> resultsLevelMap.put(res.getLevel_id(),res));
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private void setTopCount() {

        try{
            int i = Integer.parseInt(System.getProperty("itemcount"));
            TOP_COUNT = i >=1 ? i : TOP_COUNT_DEFAULT;
            logger.info("Top results count per user on level: {}",TOP_COUNT);
        } catch (Exception e){
            logger.info("Top results count per user on level is not specified or incorrect. Default: {}",TOP_COUNT_DEFAULT);
            TOP_COUNT = TOP_COUNT_DEFAULT;
        }

    }

}
