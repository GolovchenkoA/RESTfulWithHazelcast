package ua.golovchenko.artem.cache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.config.Config;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Artem on 05.08.2017.
 */
public class CacheServer {
    private static final Logger logger = LoggerFactory.getLogger(CacheServer.class);
    ConcurrentMap<Long, User> users;

    private HazelcastInstance instance;

    public CacheServer() {

        instance = Hazelcast.newHazelcastInstance();
        users = instance.getMap("users");
        User user = new UserBase("email@com.com","user2","nick2");
        user.setId(2L);
        users.put(user.getId(), user);

        for(Map.Entry<Long,User> entry:users.entrySet()){
            System.out.println("CacheServer users: " + entry.getValue());
        }

/*        logger.debug("Create Cache Client");
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("127.0.0.1");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        System.out.println(clientConfig.toString());

        logger.debug("Cache client get users");
        Map<Long,User> usersClient = client.getMap("users");
        for(Map.Entry<Long,User> entry:usersClient.entrySet()){
            System.out.println("CacheClient users: " + entry.getValue());
        }*/



    }

    public HazelcastInstance getInstance(){
        return instance;
    }

    public void stop() {
        instance.shutdown();

    }

    /*    public static void main(String[] args) {
        Config config = new Config();
        config.getNetworkConfig().setPort( 5900 );

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName( "Map_Config" )
                .setBackupCount(1);

        config.addMapConfig( mapConfig );

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance( config );
        Map<Integer, String> customers = hazelcastInstance.getMap( "users" );
    }*/
}
