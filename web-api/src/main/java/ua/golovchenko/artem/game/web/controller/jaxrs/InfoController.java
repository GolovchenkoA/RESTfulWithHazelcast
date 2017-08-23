package ua.golovchenko.artem.game.web.controller.jaxrs;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.game.cache.CacheResultService;
import ua.golovchenko.artem.game.service.ResultService;
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
    private static final String JSON_SCHEMA_INFO = "/jsonSchema/info.json";
    private ResultService resultService = new CacheResultService();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newInfo(ResultBase info){

        logger.info("newInfo(). Info: {}", info.toString());

        try {
            this.validateInput(info);
        } catch (Exception e) {
            logger.info("Invalid input object 'info': {}", info);
            logger.info("StackTrace: {}", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            resultService.add(info);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.info("Error create new user: {}", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void validateInput(ResultBase info) {
        logger.debug("Validation InfoBase: {}", info);
        org.json.JSONObject jsonSchemaResult = new JSONObject(new JSONTokener(this.getClass().getResourceAsStream(JSON_SCHEMA_INFO)));
        JSONObject jsonSubject = new JSONObject(info);
        Schema schema = SchemaLoader.load(jsonSchemaResult);
        schema.validate(jsonSubject);
    }

}
