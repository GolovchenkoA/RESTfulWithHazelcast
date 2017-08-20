package ua.artem.golovchenko.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 20.08.2017.
 *
 * @author Artem Golovchenko
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static Map<String,Boolean> settings = new HashMap<>();
    public static void main(String[] args) {

        System.setProperty("web","true");
        System.setProperty("cache","true");

        boolean web = Boolean.parseBoolean( System.getProperty("web") );
        boolean cache = Boolean.parseBoolean( System.getProperty("cache") );

        logger.info("\n Web server enabled: {} \n Cache server enabled {}", web,cache);


/*        boolean web = Boolean.parseBoolean( System.getProperty( "web" ) );
        //String webconfig = System.getProperty("webconf");
        boolean cache = Boolean.parseBoolean( System.getProperty( "cache" ) );
        //String cacheconfig = System.getProperty( "cacheconf" );

        settings.put("web_server.enable", web);
        //settings.put("web_server.config", webconfig);
        settings.put("cache_server.enable", cache);
        //settings.put("cache_server.config", cacheconfig);

        logger.info("Web server enabled: {} \n Cache server enabled {}", web,cache);*/

        try {
            RootContext.init();
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.info("Root context initialization fail. StackTrace: ", e);
            e.printStackTrace();
        }


    }
}
