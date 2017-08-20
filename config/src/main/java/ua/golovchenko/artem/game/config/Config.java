package ua.golovchenko.artem.game.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
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
    private  Properties mainProperties = new Properties();

    public Config() {}

    public Config(File config) throws IOException {
        this.load(config);
    }

    public Config(String config) throws IOException {
        this.load(config);
    }

    public void load(File config) throws IOException{

        try (InputStream inputStream =  new FileInputStream(config)) {
            logger.info("Load configuration from file: {}", config.getAbsolutePath());
            this.mainProperties.loadFromXML(inputStream);
        } catch (IOException e){
            logger.info("Error loading configuration from file : {}", config.getAbsolutePath());

        }
    }

    public void load(String file) throws IOException {

        if(logger.isDebugEnabled()){showClassPath();}

        try (InputStream inputStream =  this.getClass().getClassLoader().getResourceAsStream(file)) {
            logger.info("Load configuration from file: {}", file);
            this.mainProperties.loadFromXML(inputStream);
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

    public boolean getBoolean(String key) {return Boolean.parseBoolean(getString(key));}

    public void setKeyValue(String key, String value) {
        properties.put(key, value);
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
