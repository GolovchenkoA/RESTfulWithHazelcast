package ua.golovchenko.artem.game.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.Info;
import ua.golovchenko.artem.model.InfoBase;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

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
    ConcurrentMap<Long, User> users;
    MultiMap<Integer , Info> infoByLevel;
    MultiMap<Long , Info> infoByUser;
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
        // Init Maps

        //Users
        User user1 = new UserBase("email@com.com","user1","nick1");
        user1.setUser_id(1L);

        user1.getResults().add(new InfoBase(user1.getUser_id(),1, 1));
        user1.getResults().add(new InfoBase(user1.getUser_id(),1, 2));
        user1.getResults().add(new InfoBase(user1.getUser_id(),1, 3));
        user1.getResults().add(new InfoBase(user1.getUser_id(),1, 4));
        user1.getResults().add(new InfoBase(user1.getUser_id(),1, 5));

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
                    int result = (new Random()).nextInt(10) + 1;
                    Info info = new InfoBase(usr_id,lvl_id,result);
                    logger.info("{}. Result:{}",++count, info);
                    user.getResults().add(info);
                    infoByLevel.put(info.getLevel_id(),info);
                    infoByUser.put(info.getUser_id(),info);
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
                for(int i = 1; i <= 5; i++){
                    int result = i;
                    Info info = new InfoBase(u,l,result);
                    user.getResults().add(info);
                    logger.info("{}. Result:{}",++count1, info);
                    infoByLevel.put(info.getLevel_id(),info);
                    infoByUser.put(info.getUser_id(),info);

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

}
