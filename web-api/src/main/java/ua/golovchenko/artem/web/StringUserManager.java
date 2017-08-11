package ua.golovchenko.artem.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 */
public class StringUserManager implements UsersManager<String> {
    private static final Logger logger = LoggerFactory.getLogger(StringUserManager.class);
    private static UsersManager<User> usersManager;
    ObjectMapper mapper = new ObjectMapper();
    JSONObject json = new JSONObject();

    StringUserManager(){
       usersManager = new BaseUserManager();
    }

    StringUserManager(BaseUserManager baseUserManager){
       this.usersManager = baseUserManager;
    }


    @Override
    public String getUser(Long id) {
        return null;
    }

    @Override
    public void addUser(String user) throws IOException {

        try {
            logger.debug("in addUserMethod: {}",user);
            User newUser = mapper.readValue(user, UserBase.class);
            logger.debug("User object after convert from string: {}",newUser);
            usersManager.addUser(newUser);
            logger.debug("add User {}, all usersManager: {}",user, usersManager.findAll());
        } catch (IOException e) {
            logger.info("Error adding new user: StackTrace: {}",e);
            throw new IOException(e);
        }

    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }

    @Override
    public boolean updateUser(Long id) {
        return false;
    }

    @Override
    public Map<Long, String> findAll() throws IOException {
        Map<Long,String> users = new HashMap<>();

        for(Map.Entry<Long,User> entry:usersManager.findAll().entrySet()){
            users.put(entry.getKey(), convertUserToJSON(entry.getValue()));
        }
        return users;
    }

    private String convertUserToJSON(User user)throws JsonProcessingException {
        return mapper.writeValueAsString(user);
    }


}
