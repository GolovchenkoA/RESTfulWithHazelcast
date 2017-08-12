package ua.golovchenko.artem.game.web.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 * Wrapper for BaseUserDAO. Processes a JSON representation of a class BaseUser
 *
 * @author Golovchenko Artem
 * @see UsersManager
 */
public class StringUserDAO implements UsersManager<String> {
    private static final Logger logger = LoggerFactory.getLogger(StringUserDAO.class);
    private static UsersManager<User> usersManager;
    ObjectMapper mapper = new ObjectMapper();

    public StringUserDAO(){
       usersManager = new BaseUserDAO();
    }

    @Override
    public String getUser(Long id) {
        return null;
    }

    @Override
    public void addUser(String user) throws Exception {

        try {
            logger.debug("in addUserMethod: {}",user);
            User newUser = mapper.readValue(user, UserBase.class);
            logger.debug("User object after convert from string: {}",newUser);
            usersManager.addUser(newUser);
            logger.debug("add User {}, all usersManager: {}",user, usersManager.findAll());
        } catch (Exception e) {
            logger.info("Error adding new user. StackTrace: {}",e);
            throw new IOException(e);
        }

    }

    @Override
    public boolean deleteUser(Long id) {
        return usersManager.deleteUser(id);
    }

    @Override
    public boolean updateUser(Long id) {
        return usersManager.updateUser(id);
    }

    @Override
    public Map<Long, String> findAll() throws Exception {
        Map<Long,String> users = new HashMap<>();
        Map<Long,User> realUsers;

        try {
            realUsers = usersManager.findAll();

            for(Map.Entry<Long,User> entry:realUsers.entrySet()){
                users.put(entry.getKey(), convertUserToJSON(entry.getValue()));
            }

        } catch (Exception e) {
            logger.info("Error getting all users. StackTrace: {}", e);
            throw new IOException(e);
        }

        return users;
    }

    private String convertUserToJSON(User user)throws JsonProcessingException {
        return mapper.writeValueAsString(user);
    }


}
