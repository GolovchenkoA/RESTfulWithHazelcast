package ua.golovchenko.artem.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 05.08.2017.
 */
public class UserHandler implements HttpHandler {
        private Map<Integer, User> users = new HashMap<>();
        private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);

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

        }

        private void doGet(HttpExchange httpExchange) throws JsonProcessingException {
                User user = new UserBase("user@email.com", "user_name", "user_nick");
                user.setId(1L);

                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(user);

                respondToClient(httpExchange, jsonInString);
        }

        private void respondToClient(HttpExchange httpExchange, String result) {
                InputStream is = httpExchange.getRequestBody();

                try {
                        is.read(); // .. read the request body
                        String response = result;
                        httpExchange.sendResponseHeaders(200, response.length());
                        OutputStream out = httpExchange.getResponseBody();

                } catch (IOException e) {
                        logger.debug("Error respondToClient : {}", e);
                        e.printStackTrace();
                }



        }
}
