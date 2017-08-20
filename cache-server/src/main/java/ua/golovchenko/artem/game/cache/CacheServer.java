package ua.golovchenko.artem.game.cache;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private HazelcastInstance instance;

    public CacheServer() {
        logger.debug("Class constructor. Cache Server initialization");
        instance = Hazelcast.newHazelcastInstance();
        users = instance.getMap("users");
        User user = new UserBase("email@com.com","user2","nick2");
        user.setId(2L);
        users.put(user.getId(), user);
        User user3 = new UserBase("email3@com.com","user3","nick3");
        user3.setId(3L);
        users.put(user3.getId(), user3);

        logger.debug("Cache Server. Map (users) created. Map content: {}", users.entrySet());
    }
}
