package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by Artem on 12.08.2017.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    //private static String configPath = this.getClass().getClassLoader().getResource("cache-server-config.xml").getFile();
    public static void main(String[] args) {
        try {

            Context.init("cache-server-config.xml");
            logger.info("Application initialization complete. Current config: {}", Context.getConfig());
        } catch (Exception e) {
            logger.info("Context initialization error. StackTrace", e);
            e.printStackTrace();
        }
    }
}
