package ua.golovchenko.artem.game.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 12.08.2017.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Context.init("web-api-config.xml");
            logger.info("Web API initialization complete. Current config: {}", Context.getConfig());
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.info("Web API context initialization error. StackTrace", e);
            e.printStackTrace();
        }
    }
}
