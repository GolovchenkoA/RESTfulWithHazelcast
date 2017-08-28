package ua.golovchenko.artem.game.web.controller.jaxrs;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheLevelService;
import ua.golovchenko.artem.game.service.LevelService;
import ua.golovchenko.artem.model.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */

@Path("levelinfo")
public class LevelController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private LevelService levelService = new CacheLevelService();

    @GET
    @Path("/{level}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUsersAtLevel(@PathParam("level") Integer level) {
        logger.info("Method getTopUsersAtLevel ({}) call", level);
        Collection<User> top_users = new LinkedList<>();
        Response.ResponseBuilder builder = null;
        try {
            top_users = levelService.getTop(level);
            Gson gson = new Gson();
            String top_usersJson = gson.toJson(top_users);
            logger.debug("Get top users finish. start build response");
            builder = Response.ok(top_usersJson);
            return builder.build();
        } catch (Exception e) {
            logger.info("Error getTop top users from cache server: {}", e);
            e.printStackTrace();
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

    }
}
