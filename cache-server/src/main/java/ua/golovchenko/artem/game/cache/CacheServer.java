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

import java.util.ArrayList;
import java.util.List;
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
    private static final String RESULTS_LEVEL_MAP = "info_by_level";

    ConcurrentMap<Long, User> users;
    MultiMap<Integer , Result> infoByLevel;
    private static HazelcastInstance instance;

    public CacheServer() {
        logger.debug("Class constructor. Cache Server initialization");
        this.configureServer();
        this.configureMaps();
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
        config.getGroupConfig().setName("PRODUCTION")
                                .setPassword("production-password");
        instance = Hazelcast.newHazelcastInstance(config);


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("Shutdown cache server. instance address: {}",
                        instance.getLocalEndpoint().getSocketAddress());

                instance.shutdown();
            }
        }));

    }

    private void configureMaps() {
        users       = instance.getMap(USERS_MAP);
        infoByLevel = instance.getMultiMap(RESULTS_LEVEL_MAP);
        logger.debug("Map {} created. Map content: {}\n Map {} created content: {}",
                USERS_MAP, users.entrySet(),RESULTS_LEVEL_MAP, infoByLevel.entrySet());

        //удалить позже. для теста
        UserBase user = new UserBase(10L,"email@i.ua","myname","mynick");
        List results = new ArrayList();
        Result result1 = new ResultBase(user.getUser_id(), 2, 5);
        results.add(result1);
        user.setResults(results);

        //LevelMap
        infoByLevel.put(result1.getLevel_id(),result1);

        //UserMap
        users.put(user.getUser_id(), user);
    }

    public static HazelcastInstance getInstance(){
        return instance;
    }

}
