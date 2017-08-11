package ua.golovchenko.artem.cache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import ua.golovchenko.artem.game.config.Config;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by Artem on 05.08.2017.
 */
public class CacheServer {
    ConcurrentMap<Long, User> users;

    private HazelcastInstance instance;

    public CacheServer() {

        instance = Hazelcast.newHazelcastInstance();
        users = instance.getMap("users");
        User user = new UserBase("email@com.com","user2","nick2");
        user.setId(2L);
        users.put(user.getId(), user);
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
