package ua.golovchenko.artem.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 20.08.2017.
 *
 * @author Artem Golovchenko
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        
        boolean web = Boolean.parseBoolean( System.getProperty("web") );
        boolean cache = Boolean.parseBoolean( System.getProperty("cache") );
        int webport = Boolean.parseBoolean(System.getProperty("webport")) ? Integer.parseInt(System.getProperty("webport")) : 0;

        logger.info("\n Web server [on port: {}] enabled: {}  \n Cache server enabled {}", webport,web, cache);

        try {
            RootContext.init();
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.info("Root context initialization fail. StackTrace: ", e);
            e.printStackTrace();
        }


    }
}
