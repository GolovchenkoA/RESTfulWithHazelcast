package ua.golovchenko.artem.game.web.validators;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.Result;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public class InfoJSONValidator{
    private static final Logger logger = LoggerFactory.getLogger(InfoJSONValidator.class);

    private InfoJSONValidator(){}

    public static void validate(Result item, String pathToJsonSchema) throws Exception {
        logger.debug("Validation InfoBase: {}", item);
        org.json.JSONObject jsonSchemaResult = new JSONObject(new JSONTokener(InfoJSONValidator.class.getResourceAsStream(pathToJsonSchema)));
        JSONObject jsonSubject = new JSONObject(item);
        Schema schema = SchemaLoader.load(jsonSchemaResult);
        schema.validate(jsonSubject);
    }
}
