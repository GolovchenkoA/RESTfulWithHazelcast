package ua.golovchenko.artem.game.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 12.08.2017.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        try {
            Context.init();
            logger.info("Cache server started");
        } catch (Exception e) {
            logger.info("Error start cache server. StackTrace", e);
            e.printStackTrace();
        }
    }

}
