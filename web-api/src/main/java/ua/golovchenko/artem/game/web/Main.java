package ua.golovchenko.artem.game.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Artem on 12.08.2017.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        int webport = Integer.parseInt(System.getProperty("webport"));
        if(webport == 0){
            System.setProperty("webport","80");
        }

        try {
            Context.init();
            logger.info("Web API initialization complete.");
        } catch (Exception e) {
            logger.info("Web API context initialization error. StackTrace", e);
            e.printStackTrace();
        }
    }
}
