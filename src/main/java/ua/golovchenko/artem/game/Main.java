package ua.golovchenko.artem.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.web.WebServerApi;

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

    }
}
