package ua.golovchenko.artem.game.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.ResultBase;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Artem on 05.08.2017.
 *
 * @author Golovchenko Artem
 * A class that contains Hazelcaste Cache Server for caching data
 *
 */
public class CacheServer {
    private static final Logger logger = LoggerFactory.getLogger(CacheServer.class);
    private static final String USERS_MAP = "users";
    private static final String INFO_LEVEL_MAP = "info_by_level";
    private static final String INFO_USER_MAP = "info_by_user" ;
    private static final String TEST_MAP = "test_map" ;
    ConcurrentMap<Long, User> users;
    ConcurrentMap<Long, User> test_map;
    MultiMap<Integer , Result> infoByLevel;
    MultiMap<Long , Result> infoByUser;
    private HazelcastInstance instance;
    ManagementCenterConfig manCenter;

    public CacheServer() {
        logger.debug("Class constructor. Cache Server initialization");
        this.configureServer();
        this.configureMaps();
        logger.debug("Cache Server. Map (users) created. Map content: {}\n MultiMap content: {}", users.entrySet(), infoByLevel.entrySet());
    }

    private void configureServer() {
        logger.info("Configuring cache server");
        Config config = new Config();

        // Setup
        ManagementCenterConfig manCenter = new  ManagementCenterConfig();
        manCenter.setEnabled(true);
        manCenter.setUrl("http://localhost:8080/mancenter");
        config.setManagementCenterConfig(manCenter);
        config.setInstanceName("Game-instance");
        config.getGroupConfig().setName("PRODUCTION").setPassword("production-password");
        instance = Hazelcast.newHazelcastInstance(config);


        //config.getNetworkConfig().setPort( 5900 ).setPortAutoIncrement( false );
/*        MapConfig mapConfig = new MapConfig();
        mapConfig.setName( "testMap" )
                .setBackupCount( 2 )
                .setTimeToLiveSeconds( 300 );
        config.addMapConfig( mapConfig );*/

    }

    private void configureMaps() {
        //test synchronize map
        //this.test_synchronize_map();

        //test sort map
        //this.test_sort_map();
        // Init Maps

        //Users
        User user1 = new UserBase("email@com.com","user1","nick1");
        user1.setUser_id(1L);

        user1.getResults().add(new ResultBase(user1.getUser_id(),1, 1));
        user1.getResults().add(new ResultBase(user1.getUser_id(),1, 2));
        user1.getResults().add(new ResultBase(user1.getUser_id(),1, 3));
        user1.getResults().add(new ResultBase(user1.getUser_id(),1, 4));
        user1.getResults().add(new ResultBase(user1.getUser_id(),1, 5));

        User user2 = new UserBase("email2@com.com","user2","nick2");
        user2.setUser_id(2L);

        User user3 = new UserBase("email3@com.com","user3","nick3");
        user3.setUser_id(3L);

        //Map
/*        users = instance.getMap(USERS_MAP);
        users.put(user1.getUser_id(), user1);
        users.put(user2.getUser_id(), user2);
        users.put(user3.getUser_id(), user3);*/

        // All levels with results
        users       = instance.getMap(USERS_MAP);
        infoByLevel = instance.getMultiMap(INFO_LEVEL_MAP);
        infoByUser  = instance.getMultiMap(INFO_USER_MAP );
        logger.info("Generate users results");


        //users
        for(Long usr_id = 1l; usr_id < 25; usr_id++){
            User user = new UserBase("email@" + usr_id, "name" + usr_id, "nick" + usr_id);
            user.setUser_id(usr_id);

            // levels
            int count = 0;
            for(int lvl_id = 1; lvl_id <= 5; lvl_id++){

                //info (result)
                for(int res = 1; res <= 10; res++){
                    int i = (new Random()).nextInt(10) + 1;
                    Result result = new ResultBase(usr_id,lvl_id,i);
                    logger.info("{}. Result:{}",++count, result);
                    user.getResults().add(result);
                    infoByLevel.put(result.getLevel_id(), result);
                    infoByUser.put(result.getUser_id(), result);
                }

            }
            logger.debug("User [{}] all results: {}", user.getUser_id(), user.getResults());
            users.put(user.getUser_id(), user);
        }



        // Init user with id 100
        // levels
        int count1 = 0;
        for(int l = 1; l <= 5; l++){
            //users
            for(Long u = 100L; u <= 100; u++){
                User user = new UserBase("email@" + u, "name" + u, "nick" + u);
                user.setUser_id(u);
                //info (result)
                for(int r = 1; r <= 5; r++){
                    Result result = new ResultBase(u,l,r);
                    user.getResults().add(result);
                    logger.info("{}. Result:{}",++count1, result);
                    infoByLevel.put(result.getLevel_id(), result);
                    infoByUser.put(result.getUser_id(), result);

                }
                users.put(user.getUser_id(), user);
            }
        }



/*        Info info1 = new InfoBase(1L,1, 1);
        Info info2 = new InfoBase(1L,2, 2);
        Info info3 = new InfoBase(1L,3, 3);



        infoByLevel.put(user1.getRe, new InfoBase(1L,1,1));
        infoByLevel.put(1, new InfoBase(2L,1,2));
        infoByLevel.put(2, new InfoBase(2L,2,2));*/



/*        infoByUser.put(user1.getUser_id(), user1);
        infoByUser.put(user2.getUser_id(), user2);
        infoByUser.put(user3.getUser_id(), user3);*/
    }



    private void test_synchronize_map() {
        //test map

        //CREATE NEW USER
        logger.info("Create user #55");
        User user55 = new UserBase("email55@com.com","user55","nick55");
        user55.setUser_id(55L);

        user55.getResults().add(new ResultBase(user55.getUser_id(),1, 155));
        user55.getResults().add(new ResultBase(user55.getUser_id(),1, 255));

        logger.info("Add user #55 to test MAP");
        int NEW_RESULT = 166;
        test_map       = instance.getMap(USERS_MAP);
        test_map.put(user55.getUser_id(),user55);

        logger.info("Chage user #55 out of map");
        user55.getResults().get(0).setResult(NEW_RESULT);

        logger.info("User in map: {}", test_map.get(user55.getUser_id()).getResults());
        logger.info("User out of map: {}", user55.getResults());

        logger.info("Update user in map [Не работает не синхронизируется]");
        test_map.get(user55.getUser_id()).getResults().get(0).setResult(NEW_RESULT);

        logger.info("User in map: {}", test_map.get(user55.getUser_id()).getResults());
        logger.info("User out of map: {}", user55.getResults());

        logger.info("Изменение с синхронизацией");

        User changded_user = test_map.get(user55.getUser_id());
        changded_user.getResults().get(0).setResult(NEW_RESULT);

        test_map.put(changded_user.getUser_id(),changded_user);

        logger.info("User in map: {}", test_map.get(changded_user.getUser_id()).getResults());
        logger.info("User out of map: {}", changded_user.getResults());


    }


    private void test_sort_map() {
        ConcurrentMap<Long, User> sort_map   = instance.getMap("sort_in_map");


        //TEST SORT
        logger.info("TEST SORT");

        //CREATE NEW USER
        logger.info("Create user #66 and add to map");
        User user66 = new UserBase("email66@com.com","user66","nick66");
        user66.setUser_id(66L);

        user66.getResults().add(new ResultBase(user66.getUser_id(),1, 100));
        user66.getResults().add(new ResultBase(user66.getUser_id(),1, 200));
        user66.getResults().add(new ResultBase(user66.getUser_id(),1, 1));
        user66.getResults().add(new ResultBase(user66.getUser_id(),1, 300));

        logger.info("Source user results: {}", user66.getResults());
        sort_map.put(user66.getUser_id(),user66);


        //sort in map
        logger.info("Sort out of map");
        Collections.sort(user66.getResults());

        logger.info("user out of map: {}", user66.getResults());
        logger.info("user in map: {}", sort_map.get(user66.getUser_id()).getResults());

        logger.info("Sort in map");
        Collections.sort(sort_map.get(user66.getUser_id()).getResults());

        logger.info("user out of map: {}", user66.getResults());
        logger.info("user in map: {}", sort_map.get(user66.getUser_id()).getResults());

        logger.info("Sort out of map with synchronize");
        Collections.sort(user66.getResults());
        sort_map.put(user66.getUser_id(), user66);

        logger.info("user out of map: {}", user66.getResults());
        logger.info("user in map: {}", sort_map.get(user66.getUser_id()).getResults());



    }

}
