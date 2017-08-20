package ua.golovchenko.artem.game.web.controller;

/**
 * Created by Artem on 05.08.2017.
 *
 * @author Golovchenko Artem
 */
//public class UserHandler implements HttpHandler {
public class UserHandler{
/*
        private CRUD<String> userManager = new StringUserDAO();
        private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);


    public void handle(HttpExchange httpExchange) throws IOException {

            String method =  httpExchange.getRequestMethod().toUpperCase();
            logger.info("Method call: {}",method);

            if(method.equals("GET")){
                    doGet(httpExchange);
            } else if(method.equals("POST")){
                    doPost(httpExchange);
            }
    }

    private void doPost(HttpExchange httpExchange) {

        try(InputStream in = httpExchange.getRequestBody()){

            String userstr = IOUtils.toString(in, "UTF-8");
            logger.debug("doPost. user: {}", userstr);

            userManager.add(userstr);
            respondToClient(httpExchange, "POST OK", 201);

        } catch (Exception e) {
            logger.info("Request failed. StrackTrace: {}", e);
                    respondToClient(httpExchange, "POST Request failed", 400);
            }

    }


    private void doGet(HttpExchange httpExchange) {

        try {

            List<String> users = new ArrayList<String>(userManager.findAll().values());
            logger.info("doGet: users: {}",users);

            respondToClient(httpExchange,users.toString(),200);

        } catch (Exception e) {
            logger.info("Request failed. StrackTrace: {}", e);
            respondToClient(httpExchange, "GET Request failed", 400);
        }

    }

     private void respondToClient(HttpExchange httpExchange, String result, int response_code) {
        try(InputStream is = httpExchange.getRequestBody(); OutputStream out = httpExchange.getResponseBody()) {
                is.read(); // .. read the request body
                logger.debug("responseToClient: response text: {}",result);

                httpExchange.sendResponseHeaders(response_code, result.length());
                out.write(result.getBytes());
        } catch (IOException e) {
                logger.debug("Error respondToClient : {}", e);
                e.printStackTrace();
        }
     }

*/

}
