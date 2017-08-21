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
    ConcurrentMap<Long, User> users;
    MultiMap<Integer , Info> info;
    private HazelcastInstance instance;
    ManagementCenterConfig manCenter;

    public CacheServer() {
        logger.debug("Class constructor. Cache Server initialization");
        this.configureServer();
        this.configureMaps();
        logger.debug("Cache Server. Map (users) created. Map content: {}\n MultiMap content: {}", users.entrySet(), info.entrySet());
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
        users = instance.getMap("users");
        User user = new UserBase("email@com.com","user2","nick2");
        user.setUser_id(2L);
        users.put(user.getUser_id(), user);
        User user3 = new UserBase("email3@com.com","user3","nick3");
        user3.setUser_id(3L);
        users.put(user3.getUser_id(), user3);

        // All levels with results

        info = instance.getMultiMap( "info_by_level" );
        info.put(1, new InfoBase(1L,1,1));
        info.put(1, new InfoBase(2L,1,2));
        info.put(2, new InfoBase(2L,2,2));
    }

}
