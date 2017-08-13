package ua.golovchenko.artem.game.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

/**
 * Created by Artem on 07.08.2017.
 *
 * Applicationn Configuration
 */
public class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private final Properties properties = new Properties();

    public void load(String file) throws IOException {
        Properties mainProperties = new Properties();

        if(logger.isDebugEnabled()){showClassPath();}

        try (InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(file)) {
            logger.info("Load configuration from file: {}", file);
            mainProperties.loadFromXML(inputStream);
        }

        String defaultConfigFile = mainProperties.getProperty("config.default");

        if (defaultConfigFile != null) {
            logger.info("Loading configuration from file: config.default");
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(defaultConfigFile)) {
                properties.loadFromXML(inputStream);
            }
        }

//        logger.info("Loading configuration from file: {}", file);
        properties.putAll(mainProperties); // override defaults

    }

    public boolean hasKey(String key) {
        return properties.containsKey(key);
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public String getString(String key, String defaultValue) {
        return hasKey(key) ? getString(key) : defaultValue;
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }


    @Override
    public String toString() {
        return "Config{" +
                "properties=" + properties +
                '}';
    }

    private void showClassPath(){
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls) {
            System.out.println(url.getFile());
        }
    }
}
