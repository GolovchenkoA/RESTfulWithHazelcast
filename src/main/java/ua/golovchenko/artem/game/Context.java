package ua.golovchenko.artem.game;

import ua.golovchenko.artem.cache.CacheServer;
import ua.golovchenko.artem.game.ServerManager;
import ua.golovchenko.artem.game.config.Config;
import ua.golovchenko.artem.web.Web;

/**
 * Created by Artem on 07.08.2017.
 */
public final class Context {
    private static final logger

    private static Config config;
    private static CacheServer cacheServer;
    private static Web webServer;
    private static DataManager dataManager;

    private Context() {
    }

    public static Config getConfig() {
        return config;
    }

    public static void init(String file) throws Exception {
        config = new Config();
        config.load(file);


        if (config.hasKey("cache_server.enable") && config.hasKey("cache_server.configFile")) {

            cacheServer = new CacheServer(config);
        }

    }

    public static DataManager getDataManager() {
        return dataManager;
    }
}
