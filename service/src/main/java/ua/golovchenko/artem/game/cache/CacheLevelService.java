package ua.golovchenko.artem.game.cache;


import com.hazelcast.core.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.service.LevelService;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.Info;
import ua.golovchenko.artem.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    @Override
    public List<User> getTop(Integer level, Integer topCount) throws Exception {
        MultiMap<Integer, Info> map = dataManager.getCache().getMultiMap(INFO_LEVEL_MAP);
        logger.info("getTop users on level {}. All users: {}", level, map);
        Collection<Info> allResultsOnLevel = map.get(level);

        //////////////////Пример http://howtodoinjava.com/java-8/java-stream-distinct-examples/

        // 1. Сортировать результаты на уровне по убыванию
        allResultsOnLevel.stream().sorted((r1, r2) -> r2.getResult().compareTo(r1.getResult()));
        //1.1  и оставить уникальные результаты (по признаку id-пользователя)
        List<Info> uniqueAllResultsByUser = allResultsOnLevel.stream().filter(distinctByKey(o -> o.getUser_id())).collect(Collectors.toList());

        // 2. Надо выбрать топ уникальных пользователей
        int end = (uniqueAllResultsByUser.size() < TOP_COUNT) ? uniqueAllResultsByUser.size() : TOP_COUNT;
        List<Info>  uniqueTopResultsByUser = uniqueAllResultsByUser.subList(0,end);

        //3. Получаем список ID пользователей
        List<Long> usersIds = uniqueTopResultsByUser.stream().map(Info::getUser_id).collect(Collectors.toList());

        // По этим пользователям надо получить все результаты на уровне
        // По этому мы должны с общего списка удалить все результаты, кроме этих айдишников

        List<Info> allResultsOfTopUsersOnLevel =
                allResultsOnLevel.stream().filter(res -> usersIds.stream().
                allMatch(id -> res.getUser_id().equals(id))).collect(Collectors.toList());


        List<User> users = allResultsOfTopUsersOnLevel.stream().
        //4. Надо вернуть пользователей (с результатами) в порядке убывания
        // Необходимо сравнить результаты пользователей


        List<User> users = allResultsOfTopUsers.stream()

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


        return allResultsOfTopUsers;
    }


    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private static <T> Predicate<T> contains


}
