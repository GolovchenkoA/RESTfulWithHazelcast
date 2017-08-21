package ua.golovchenko.artem.game.web.controller.jaxrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheInfoService;
import ua.golovchenko.artem.game.service.InfoService;
import ua.golovchenko.artem.model.Info;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */

@Path("setinfo")
public class InfoController {
    private InfoService infoService = new CacheInfoService();
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newInfo(Info info){

        logger.info("newInfo(). Info: {}", info);

        try {
            infoService.add(info);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.info("Error create new user: {}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
