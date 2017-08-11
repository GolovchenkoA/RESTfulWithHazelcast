package ua.golovchenko.artem.cache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import ua.golovchenko.artem.game.config.Config;

/**
 * Created by Artem on 05.08.2017.
 */
public class CacheServer {
    private HazelcastInstance instance;

    public CacheServer() {
        instance = Hazelcast.newHazelcastInstance();
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
