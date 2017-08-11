package ua.golovchenko.artem.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.StringUserManager;
import ua.golovchenko.artem.UsersManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Artem on 05.08.2017.
 */
public class UserHandler implements HttpHandler {
        //private Map<Long, User> users = new HashMap<>();
        UsersManager<String> userManager = new StringUserManager();
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
        try(InputStream in = httpExchange.getRequestBody()){
                String userstr = IOUtils.toString(in, "UTF-8");
                logger.debug("doPost. user: {}", userstr);

                userManager.addUser(userstr);
                respondToClient(httpExchange, "POST OK", 201);

            } catch (IOException e) {
                    logger.debug("Error doPost : {}", e);
                    respondToClient(httpExchange, "POST Request failed", 400);
            }

    }


/*    private void saveUser(User user) {
        Long id = user.getId();
        if(id != null){
            this.users.put((id), user);
        }else {
            throw new IllegalArgumentException("User id is null");
        }

    }*/

    private void doGet(HttpExchange httpExchange) {
/*                User user = new UserBase("user@email.com", "user_name", "user_nick");
                user.setId(1L);*/
        try {
            List<String> users = new ArrayList<String>(userManager.findAll().values());
            logger.info("doGet: users: {}",users);
            respondToClient(httpExchange,users.toString(),200);
        } catch (IOException e) {
            logger.info("Request failed. StrackTrace: {}", e);
            respondToClient(httpExchange, "GET Request failed", 400);
        }


    }

     private void respondToClient(HttpExchange httpExchange, String result, int response_code) {
        try(InputStream is = httpExchange.getRequestBody(); OutputStream out = httpExchange.getResponseBody()) {
                is.read(); // .. read the request body
            String response = result;

            logger.debug("responseToClient: response text: {}",response);

                httpExchange.sendResponseHeaders(response_code, response.length());
                out.write(response.getBytes());
        } catch (IOException e) {
                logger.debug("Error respondToClient : {}", e);
                e.printStackTrace();
        }
     }


}
