package ua.golovchenko.artem.game.web.controller.jaxrs;

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
    private Set<Class<?>> classes = null;

    public JaxRsApplication() {
        Set<Class<?>> c = new HashSet<Class<?>>();
        c.add(UsersHandler.class);
        classes = Collections.unmodifiableSet(c);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
