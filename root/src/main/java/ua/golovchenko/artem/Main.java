package ua.golovchenko.artem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 07.08.2017.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {


        try {
            Context.init("game.xml");
            logger.info("Application initialization complete. Current config: {}", Context.getConfig());
        } catch (Exception e) {
            logger.info("Context initialization error. StackTrace", e);
            e.printStackTrace();
        }


    }
}
