package ua.golovchenko.artem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.Context;

/**
 * Created by Artem on 07.08.2017.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        Context.init("game.xml");
        logger.info("Application initialization complete. Current config: {}", Context.getConfig());

    }
}
