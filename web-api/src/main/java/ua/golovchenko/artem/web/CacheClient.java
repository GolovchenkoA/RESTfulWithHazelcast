package ua.golovchenko.artem.web;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.StringUserManager;
import ua.golovchenko.artem.game.Context;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Artem on 12.08.2017.
 */
public class CacheClient implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(CacheClient.class);
    private HazelcastInstance client;

    public CacheClient() throws Exception{
        logger.debug("Class constructor");
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("127.0.0.1");
        client = HazelcastClient.newHazelcastClient(clientConfig);
        System.out.println(clientConfig.toString());

/*        logger.debug("Try to load map from server");
        Context.getDataManager().getCacheDataManager().getCacheClient().getCache().getMap("users");*/
    }

    public HazelcastInstance getCache(){
        return client;
    }

    @Override
    public void close() throws IOException {
        client.shutdown();
        logger.debug("Hazelcast client closed");
    }



    /*    BlockingQueue<String> queue = client.getQueue("queue");

    public HazelcastInstance getCache() {
        return client;
    }

    queue.put("Hello!");
    System.out.println("Message sent by Hazelcast Client!");*/

    //HazelcastClient.shutdownAll();
}
