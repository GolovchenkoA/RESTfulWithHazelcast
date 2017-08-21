package ua.golovchenko.artem.game.web.validators;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.ResultBase;


public class ResultJSONValidatorTest {
    private JSONObject jsonSchema;

    @Before
    public void init(){
        jsonSchema = new JSONObject(new JSONTokener(ResultJSONValidatorTest.class.getResourceAsStream("/jsonSchema/result.json")));
    }

    @Test
    public void testValid(){
        JSONObject jsonSubject = new JSONObject(new JSONTokener(ResultJSONValidatorTest.class.getResourceAsStream("/jsonSchema/result_valid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }



    @Test(expected = ValidationException.class)
    public void testInvalid(){
        JSONObject jsonSubject = new JSONObject(new JSONTokener(ResultJSONValidatorTest.class.getResourceAsStream("/jsonSchema/result_invalid.json")));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }

    @Test
    public void testValidUserBase(){
        JSONObject jsonSubject = new JSONObject(new ResultBase(1L,1L,5));
        System.out.println(jsonSubject);
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }


    @Test(expected = ValidationException.class)
    public void testInvalidUserBase(){
        Result result = new ResultBase();
        result.setResult(5L);

        JSONObject jsonSubject = new JSONObject(result);
        System.out.println(jsonSubject);
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }


}