package ua.golovchenko.artem.game.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.User;

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
    private static final String RESULTS_USER_MAP = "info_by_user" ;
    ConcurrentMap<Long, User> users;
    MultiMap<Integer , Result> infoByLevel;
    MultiMap<Long , Result> infoByUser;
    private HazelcastInstance instance;

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
        users       = instance.getMap(USERS_MAP);
        infoByLevel = instance.getMultiMap(RESULTS_LEVEL_MAP);
        infoByUser  = instance.getMultiMap(RESULTS_USER_MAP);
    }

}
