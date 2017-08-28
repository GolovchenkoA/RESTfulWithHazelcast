package ua.golovchenko.artem.game.web.controller.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheResultService;
import ua.golovchenko.artem.game.service.ResultService;
import ua.golovchenko.artem.game.web.validators.InfoJSONValidator;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.ResultBase;

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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String JSON_SCHEMA_INFO = "/jsonSchema/info.json";
    private ResultService resultService = new CacheResultService();
    ObjectMapper mapper = new ObjectMapper();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newInfo(String jsonInfo){
        Gson gson = new Gson();
        Result info = gson.fromJson(jsonInfo,ResultBase.class);
        
        logger.info("newInfo(). Info: {}", info.toString());
        try {
            InfoJSONValidator.validate(info, JSON_SCHEMA_INFO);
        } catch (Exception e) {
            logger.info("Invalid input object 'info': {}", info);
            logger.info("StackTrace: {}", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            this.resultService.add(info);
            String result = "Product created : " + info;
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.info("Error add result. StackTrace: {}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
