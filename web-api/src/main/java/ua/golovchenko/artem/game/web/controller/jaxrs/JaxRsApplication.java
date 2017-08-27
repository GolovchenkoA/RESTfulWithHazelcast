package ua.golovchenko.artem.game.web.controller.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artem on 13.08.2017.
 */

@ApplicationPath("resources")
public class JaxRsApplication extends Application {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Set<Class<?>> classes = null;

    public JaxRsApplication() {
        logger.info("Start setup jersey");
        Set<Class<?>> c = new HashSet<Class<?>>();
        c.add(UserController.class);
        c.add(InfoController.class);
        c.add(LevelController.class);
        classes = Collections.unmodifiableSet(c);
        logger.info("Finish setup jersey");
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
