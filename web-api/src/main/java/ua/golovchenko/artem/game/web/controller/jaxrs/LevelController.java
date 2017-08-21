package ua.golovchenko.artem.game.web.controller.jaxrs;

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
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */

@Path("levelinfo")
public class LevelController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private LevelService levelService = new CacheLevelService();
    private static final int TOP_COUNT = 20;

    @GET
    @Path("/{level}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUsersAtLevel(@PathParam("level") Integer level) {
        logger.info("Method getTopUsersAtLevel ({}) call", level);
        List<User> top_users = new LinkedList<>();
        Response.ResponseBuilder builder = null;
        try {

            top_users = levelService.get(level,TOP_COUNT);
            builder = Response.ok(top_users);
            return builder.build();
        } catch (Exception e) {
            logger.info("Error get top users from cache server: {}", e);
            e.printStackTrace();
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

    }
}
