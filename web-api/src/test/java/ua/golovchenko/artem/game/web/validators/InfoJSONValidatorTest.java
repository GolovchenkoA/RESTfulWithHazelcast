package ua.golovchenko.artem.game.web.validators;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;
import ua.golovchenko.artem.model.Info;
import ua.golovchenko.artem.model.InfoBase;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;


public class InfoJSONValidatorTest {
    private static final String JSON_SCHEMA_INFO = "/jsonSchema/info.json";
    private static final String JSON_INFO_VALID = "/jsonSchema/info_valid.json";
    private static final String JSON_INFO_INVALID = "/jsonSchema/info_invalid.json";
    private static final String JSON_SCHEMA_USER = "/jsonSchema/user.json";
    private static final String JSON_USER_VALID = "/jsonSchema/user_valid.json";
    private static final String JSON_USER_INVALID = "/jsonSchema/user_invalid.json";

    private JSONObject jsonSchemaResult;
    private JSONObject jsonSchemaUser;

    @Before
    public void init(){
        jsonSchemaResult = new JSONObject(new JSONTokener(InfoJSONValidatorTest.class.getResourceAsStream(JSON_SCHEMA_INFO)));
        jsonSchemaUser = new JSONObject(new JSONTokener(InfoJSONValidatorTest.class.getResourceAsStream(JSON_SCHEMA_USER)));
    }

    @Test
    public void testValidResult(){
        JSONObject jsonSubject = new JSONObject(new JSONTokener(InfoJSONValidatorTest.class.getResourceAsStream(JSON_INFO_VALID)));

        Schema schema = SchemaLoader.load(jsonSchemaResult);
        schema.validate(jsonSubject);
    }



    @Test(expected = ValidationException.class)
    public void testInvalidResult(){
        JSONObject jsonSubject = new JSONObject(new JSONTokener(InfoJSONValidatorTest.class.getResourceAsStream(JSON_INFO_INVALID)));

        Schema schema = SchemaLoader.load(jsonSchemaResult);
        schema.validate(jsonSubject);
    }

    @Test
    public void testValidResultBase(){
        JSONObject jsonSubject = new JSONObject(new InfoBase(1L,1L,5));
        System.out.println(jsonSubject);
        Schema schema = SchemaLoader.load(jsonSchemaResult);
        schema.validate(jsonSubject);
    }


    @Test(expected = ValidationException.class)
    public void testInvalidResultBase(){
        Info info = new InfoBase();
        info.setResult(5L);

        JSONObject jsonSubject = new JSONObject(info);
        System.out.println(jsonSubject);
        Schema schema = SchemaLoader.load(jsonSchemaResult);
        schema.validate(jsonSubject);
    }


    @Test
    public void testValidUser(){
        JSONObject jsonSubject = new JSONObject(new JSONTokener(InfoJSONValidatorTest.class.getResourceAsStream(JSON_USER_VALID)));

        Schema schema = SchemaLoader.load(jsonSchemaUser);
        schema.validate(jsonSubject);
    }



    @Test(expected = ValidationException.class)
    public void testInvalid(){
        JSONObject jsonSubject = new JSONObject(new JSONTokener(InfoJSONValidatorTest.class.getResourceAsStream(JSON_USER_INVALID)));

        Schema schema = SchemaLoader.load(jsonSchemaUser);
        schema.validate(jsonSubject);
    }

    @Test
    public void testValidUserBase(){
        User user = new UserBase();
        user.setUser_id(1L);
        JSONObject jsonSubject = new JSONObject(user);

        Schema schema = SchemaLoader.load(jsonSchemaUser);
        schema.validate(jsonSubject);
    }


    @Test(expected = ValidationException.class)
    public void testInvalidUserBase(){
        User user = new UserBase();
        JSONObject jsonSubject = new JSONObject(user);

        Schema schema = SchemaLoader.load(jsonSchemaUser);
        schema.validate(jsonSubject);
    }



    @Test
    public void testvalidUserBaseString(){
        String user = "{user_id: 1}";
        JSONObject jsonSubject = new JSONObject(user);

        Schema schema = SchemaLoader.load(jsonSchemaUser);
        schema.validate(jsonSubject);
    }




    @Test(expected = ValidationException.class)
    public void testInvalidUserBaseString(){
        String user = "{user_id: 'no_number'}";
        JSONObject jsonSubject = new JSONObject(user);

        Schema schema = SchemaLoader.load(jsonSchemaUser);
        schema.validate(jsonSubject);
    }





}