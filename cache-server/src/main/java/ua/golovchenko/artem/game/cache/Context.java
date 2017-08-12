package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.config.Config;

import java.io.IOException;

/**
 * Created by Artem on 12.08.2017.
 */
public class Context {
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    private static Config config;
    private static CacheServer cacheServer;
    private static final String CACHE_SERVER = "cache_server.enable";

    /**
     *
     * @param file - configuration file name. Example : "config.xml"
     * @throws IOException
     */

    public static void init(String file) throws IOException {
/*        logger.info("PATH: {}",Context.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        logger.info("File: {}",Context.class.getProtectionDomain().getCodeSource().getLocation().getFile());*/

        //System.out.println("Config file::: " + Context.class.getResource("cache-server-config.xml").getFile());
        //System.out.println("Config file::: " + Resources.getResource(("./" + file)));

        try {
            config = new Config();
            config.load(file);

        } catch (Exception e) {
            logger.info("Error load configuration file: {}", file);
            throw new IOException(e);
        }

        // Cache Server Configuration
        if (config.hasKey(CACHE_SERVER) && config.getBoolean(CACHE_SERVER)) {
            logger.info("Config. Cache server: enabled");
            cacheServer = new CacheServer();
        }else {
            logger.info("Config. Cache server disabled");
        }
    }

    public static Config getConfig() {
        return config;
    }
}
