package ua.golovchenko.artem.game.cache.dao;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 12.08.2017.
 *
 * @author Golovchenko Artem
 */
public class CacheClient {
    private static final Logger logger = LoggerFactory.getLogger(CacheClient.class);
    private HazelcastInstance client;

    public CacheClient() throws Exception{
        logger.debug("Class constructor");
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("127.0.0.1");
        clientConfig.getGroupConfig().setName("PRODUCTION").setPassword("production-password");
        this.client = HazelcastClient.newHazelcastClient(clientConfig);
        System.out.println(clientConfig.toString());

/*        logger.debug("Try to load map from server");
        Context.getDataManager().getCacheDataManager().getCacheClient().getCache().getMap("users");*/
    }

    public HazelcastInstance getCache(){
        return client;
    }

}
