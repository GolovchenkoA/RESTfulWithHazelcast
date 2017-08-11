package ua.golovchenko.artem.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 05.08.2017.
 */
public class UserHandler implements HttpHandler {
        private Map<Long, User> users = new HashMap<>();
        private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);
        private static UsersManager<String> userManager = new StringUserManager();

    public void handle(HttpExchange httpExchange) throws IOException {
            String method =  httpExchange.getRequestMethod().toUpperCase();
            logger.info("Method call: {}",method);


            if(method.equals("GET")){
                    doGet(httpExchange);
            } else if(method.equals("POST")){
                    doPost(httpExchange);
            }
/*        Map<String, Object> data = new HashMap<String, Object>();
        data.put( "name", "Mars" );
        data.put( "age", 32 );
        data.put( "city", "NY" );
        JSONObject json = new JSONObject();
        json.putAll( data );
        System.out.printf( "JSON: %s", json.toString(2) )*/
        ;

            //if(t.getRequestMethod().equals(Http))


    }

    private void doPost(HttpExchange httpExchange) {
            try(InputStream in = httpExchange.getRequestBody()){

                String userstr = IOUtils.toString(in, "UTF-8");
                User user = getUserFromString(userstr);
                saveUser(user);
                respondToClient(httpExchange, "POST finish");
            } catch (IOException e) {
                    logger.debug("Error doPost : {}", e);
            }

    }

    private User getUserFromString(String userstr) {


    }

    private void saveUser(User user) {
        Long id = user.getId();
        if(id != null){
            this.users.put((id), user);
        }else {
            throw new IllegalArgumentException("User id is null");
        }

    }

    private void doGet(HttpExchange httpExchange) {
/*                User user = new UserBase("user@email.com", "user_name", "user_nick");
                user.setId(1L);*/
        try {
            respondToClient(httpExchange, this.getJsonUsers());
        } catch (JsonProcessingException e) {
            logger.debug("Error get all users : {}", e);
        }
    }

     private void respondToClient(HttpExchange httpExchange, String result) {
        try(InputStream is = httpExchange.getRequestBody(); OutputStream out = httpExchange.getResponseBody()) {
                is.read(); // .. read the request body
                String response = result;
                httpExchange.sendResponseHeaders(200, response.length());
                out.write(response.getBytes());
        } catch (IOException e) {
                logger.debug("Error respondToClient : {}", e);
                e.printStackTrace();
        }
     }

    public String getJsonUsers()throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(users);
    }
}
