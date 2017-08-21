package ua.golovchenko.artem.game.cache;


import com.hazelcast.core.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.dao.DataManager;
import ua.golovchenko.artem.game.service.LevelService;
import ua.golovchenko.artem.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public class CacheLevelService implements LevelService{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String LEVELS_MAP = "info_by_level";
    DataManager dataManager = new DataManager();

    @Override
    public List<User> get(Integer level, Integer topCount) throws Exception {
        MultiMap<Integer, User> map = dataManager.getCache().getMultiMap(LEVELS_MAP);
        logger.info("get users on level {}. All users: {}", level, map);
        Collection<User> values = map.get(level);
/*        for ( Integer key : map.keySet() ){
            logger.info("ALL get users on level {}. key: {} , value", level, key);
            if(key == level){
                map.keySet().stream().forEach(key.equals(level)).collect(Collectors.toList());
                values.add();
                logger.info("EQUALS get users on level {}. key: {} , value", level, key);
            }
        }*/

/*        Collection<User> values = new LinkedList<>();
        for (Integer key : map.keySet()) {
            logger.info("get users on level {}. users(all): {}", level, key);
            if (key == level) {
                values.add(map.get(key));
            }

            //System.out.println( "%s -> %s\n",key, values );

            //return dataManager.getCache().getMap(LEVELS_MAP).get
            //.stream().filter(entry -> entry.getKey().equals(level)).collect(Collectors.toList());

        }*/


        return (List)values;
    }
}
