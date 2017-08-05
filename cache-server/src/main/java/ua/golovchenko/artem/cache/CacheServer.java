package ua.golovchenko.artem.cache;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

/**
 * Created by Artem on 05.08.2017.
 */
public class CacheServer {

    public static void main(String[] args) {
        Config config = new Config();
        config.getNetworkConfig().setPort( 5900 );

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName( "Map_Config" )
                .setBackupCount(1);

        config.addMapConfig( mapConfig );

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance( config );
        Map<Integer, String> customers = hazelcastInstance.getMap( "users" );
    }
}
