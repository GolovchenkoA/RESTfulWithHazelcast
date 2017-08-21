package ua.golovchenko.artem.game.web.controller.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheUserService;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by Artem on 13.08.2017.
 */
@Path("userinfo")
public class UserController {
    private UserService userService = new CacheUserService();
    //private CRUD<String> userManager = new StringUserDAO();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
            logger.info("Error get all user from cache server: {}", e);
            e.printStackTrace();
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

 /*   private void jsonValidation(User user) {
        JsonSchemaFactory jsonSchemaFactory =  JsonSchemaFactory.getJsonSchema(JsonNode)

    }*/

/*      //Save User
        @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(@Valid UserBase user){

        try {
            userService.add(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.info("Error create new user: {}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
*/



    /*
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(@Context ContainerRequest request){
        InputStream in = request.getEntityStream();
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(in,User.class);
            userService.add(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.info("Error create new user: {}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        //User user = mapper.readValues(in, UserBase.class)

    }*/


    //@PUT   convert json to pojo https://stackoverflow.com/questions/21918081/jersey-2-6-with-jackson-json-deserialization



    /*    with manager dao

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StreamingOutput getUserResults(@PathParam("id") int id) {
        try {
            List<String> users = new ArrayList<String>(userManager  .findAll().values());
            logger.info("doGet: users: {}",users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ;
    }



    */
}
