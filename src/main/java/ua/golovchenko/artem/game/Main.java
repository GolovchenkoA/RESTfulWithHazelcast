package ua.golovchenko.artem.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.net.InetAddress.getLocalHost;

/**
 * Created by Artem on 07.08.2017.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        Context.init("game.xml");
        logger.info("Application initialization complete. Current config: {}", Context.getConfig());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    logger.info("Shutting down server [ip]: {}", getLocalHost().getAddress());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }

                if (Context.getWebApiServer() != null) {
                    Context.getWebApiServer().stop(5);
                }

                 if (Context.getCacheServer() != null) {
                    Context.getCacheServer().stop();
                }

            }
        });
    }
}
