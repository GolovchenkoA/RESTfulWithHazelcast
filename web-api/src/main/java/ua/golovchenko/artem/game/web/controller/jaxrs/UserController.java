package ua.golovchenko.artem.game.web.controller.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheUserService;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Artem on 13.08.2017.
 */
@Path("userinfo")
public class UserController {
    private UserService userService = new CacheUserService();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int TOP_RESULTS = 4;

/*
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StreamingOutput getUserResults(@PathParam("id") int id) {
        try {
            Map<Long, User> users = userService.findAll();
            logger.info("doGet: users: {}",users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ;
    }
*/

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserResults() {
        Map<Long,User> users = null;
        Response.ResponseBuilder builder = null;
        try {
            users = userService.findAll();
            builder = Response.ok(users.entrySet());
            return builder.build();
        } catch (Exception e) {
            logger.info("Error getTop all user from cache server: {}", e);
            e.printStackTrace();
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

    }


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
        } catch (Exception e) {
            logger.info("Error get top results. user with id {} from cache server: {}",id, e);
            e.printStackTrace();
            if(e.getMessage().startsWith("User not found. id:"))
                return Response.status(Response.Status.NO_CONTENT).build();
            else
                return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(UserBase user){

        logger.info("newUser(). User: {}", user);

        try {
            userService.add(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.info("Error create new user: {}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
