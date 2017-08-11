package ua.golovchenko.artem.game.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Artem on 07.08.2017.
 *
 * Applicationn Configuration
 */
public class Config {
    private final Properties properties = new Properties();
    private static Properties config;

    public void load(String file) throws IOException {
        Properties mainProperties = new Properties();

        try (InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(file)) {
            mainProperties.loadFromXML(inputStream);
        }

        String defaultConfigFile = mainProperties.getProperty("config.default");

        if (defaultConfigFile != null) {
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(defaultConfigFile)) {
                properties.loadFromXML(inputStream);
            }
        }

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


    @Override
    public String toString() {
        return "Config{" +
                "properties=" + properties +
                '}';
    }
}
