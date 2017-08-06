package ua.golovchenko.artem.game;

import ua.golovchenko.artem.game.ServerManager;

/**
 * Created by Artem on 07.08.2017.
 */
public final class Context {

    private static ServerManager serverManager;

    private Context() {
    }

    private static Config config;

    public static Config getConfig() {
        return config;
    }

    public static void init(String file) throws Exception {
        config = new Config();
        config.load(file);
    }

    public static ServerManager getServerManager() {
        return serverManager;
    }
}
