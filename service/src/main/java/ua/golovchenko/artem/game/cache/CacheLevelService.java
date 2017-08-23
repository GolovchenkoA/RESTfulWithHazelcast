package ua.golovchenko.artem.game.cache;


import com.hazelcast.core.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.service.LevelService;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
    private static final String INFO_LEVEL_MAP = "info_by_level";
    private static final int TOP_COUNT = 2;
    DataManager dataManager = new DataManager();


    /**
     *
     * @param level Level number
     * @param topCount Maximum number of users
     * @return Top users with results on this level
     * @throws Exception
     */

    @Override
    public List<Result> getTop(Integer level, Integer topCount) throws Exception {
        logger.debug("Generate report of top users result on level {}.", level);
        MultiMap<Integer, Result> map = dataManager.getCache().getMultiMap(INFO_LEVEL_MAP);
        logger.debug("getTop users on level {}. All users: {}", level, map);
        Collection<Result> allResultsOnLevel = map.get(level);
        List<Result> allResultsOnLevelModifiedList = new ArrayList<>(allResultsOnLevel);
        logger.debug(" Не сортированый список:\n {}", allResultsOnLevelModifiedList);
        //////////////////Пример http://howtodoinjava.com/java-8/java-stream-distinct-examples/

        // 1. Сортировать результаты на уровне по убыванию
        logger.debug("// 1. Сортировать результаты на уровне по убыванию");
        Collections.sort((List) allResultsOnLevelModifiedList);
        //allResultsOnLevel.stream().sorted((r1, r2) -> r2.getResult().compareTo(r1.getResult()));
        logger.debug("Сортированый список:\n {}", allResultsOnLevelModifiedList);

        //1.1  и оставить уникальные результаты (по признаку id-пользователя)
        logger.debug("//1.1  и оставить уникальные результаты (по признаку id-пользователя)");
        List<Result> uniqueAllResultsByUser = allResultsOnLevelModifiedList.stream().filter(distinctByKey(o -> o.getUser_id())).collect(Collectors.toList());
        logger.debug("Уникальные результаты на уровне:\n {}", uniqueAllResultsByUser);

        // 2. Надо выбрать топ уникальных пользователей
        logger.debug("// 2. Надо выбрать топ уникальных пользователей");
        int end = (uniqueAllResultsByUser.size() < TOP_COUNT) ? uniqueAllResultsByUser.size() : TOP_COUNT;
        List<Result>  uniqueTopResultsByUser = uniqueAllResultsByUser.subList(0,end);
        logger.debug("Топ результатов уникальных пользователей:\n {}",uniqueTopResultsByUser);

        //3. Получаем список ID пользователей
        logger.debug("//3. Получаем список ID пользователей");
        List<Long> usersIds = uniqueTopResultsByUser.stream().map(Result::getUser_id).collect(Collectors.toList());
        logger.debug("//3.ID пользователей из TOP [{}] результатов: {}",TOP_COUNT, usersIds);
        // По этим пользователям надо получить все результаты на уровне
        // По этому мы должны с общего списка удалить все результаты, кроме этих айдишников
        logger.debug("получаем все результаты топовых пользователей на этом уровне");
        List<Result> allResultsOfTopUsersOnLevel = new ArrayList<>();
/*                allResultsOnLevelModifiedList.stream().filter(res -> usersIds.stream().
                allMatch(id -> res.getUser_id().equals(id))).collect(Collectors.toList());
        */

        for(int u = 0; u < usersIds.size(); u++){
            final int userId = u;
            allResultsOfTopUsersOnLevel.addAll((allResultsOnLevelModifiedList.stream().filter(res -> res.getUser_id().equals(usersIds.get(userId)))).collect(Collectors.toList()));
        }

        logger.debug("Список результатов:\n {}", allResultsOfTopUsersOnLevel);


        //4.!!!!!!!!!!! Надо вернуть пользователей (с результатами) в порядке убывания
        // Необходимо сравнить результаты пользователей







        //List<User> users = allResultsOfTopUsers.stream()

/*        List<Info> allResultsOfTopUsers = allResultsOnLevel.stream().
                filter(res -> (usersIds.stream().
                        forEach(id -> res.getUser_id().equals(id)))).collect(Collectors.toList());

        List<Info> allResultsOfTopUsers2 = usersIds.stream().forEach(
                id -> (allResultsOnLevel.stream().
                        filter(res -> res.getUser_id().equals(id))
                )
        );

        List<Info> allResultsOfTopUsers = usersIds.stream().forEach(allResultsOnLevel.stream().fi);*/


/*        for ( Integer key : map.keySet() ){
            logger.info("ALL getTop users on level {}. key: {} , value", level, key);
            if(key == level){
                map.keySet().stream().forEach(key.equals(level)).collect(Collectors.toList());
                values.add();
                logger.info("EQUALS getTop users on level {}. key: {} , value", level, key);
            }
        }*/

/*        Collection<User> values = new LinkedList<>();
        for (Integer key : map.keySet()) {
            logger.info("getTop users on level {}. users(all): {}", level, key);
            if (key == level) {
                values.add(map.getTop(key));
            }

            //System.out.println( "%s -> %s\n",key, values );

            //return dataManager.getCache().getMap(LEVELS_MAP).getTop
            //.stream().filter(entry -> entry.getKey().equals(level)).collect(Collectors.toList());

        }*/

        return allResultsOfTopUsersOnLevel;
    }

    @Override
    public void update(User user) throws Exception {
        logger.debug("Updating user [id]: {} results in map: {}", user.getUser_id(),INFO_LEVEL_MAP);
        MultiMap<Integer, Result> map = dataManager.getCache().getMultiMap(INFO_LEVEL_MAP);
        map.values().removeIf(res -> res.getUser_id().equals(user.getUser_id()));
        user.getResults().forEach(res -> map.put(res.getLevel_id(),res));
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
