package ua.golovchenko.artem.game.web.controller.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheUserService;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.Result;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

/**
 * Created by Artem on 13.08.2017.
 */
@Path("userinfo")
public class UserController {
    private UserService userService = new CacheUserService();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int TOP_RESULTS = 4;
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserResultsByUserId(@PathParam("id") Long id) {
        List<Result> users = null;
        Response.ResponseBuilder builder = null;
        try {
            Collection<Result> results = userService.getTop(id, TOP_RESULTS);
            builder = Response.ok(results);
            return builder.build();
        } catch(NullPointerException e){
            logger.info("User not found. id: [{}]",id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        catch (Exception e) {
            logger.info("Error get top results. user with id {} from cache server: {}",id, e);
            e.printStackTrace();
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
    }
}
