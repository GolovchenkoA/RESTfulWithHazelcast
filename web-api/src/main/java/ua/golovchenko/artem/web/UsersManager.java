package ua.golovchenko.artem.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import ua.golovchenko.artem.model.User;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 */
public interface UsersManager<T> {
    public T getUser(Long id);
    public void addUser(T user) throws IOException;
    public boolean deleteUser(Long id);
    public boolean updateUser(Long id);
    public Map<Long,T> findAll() throws IOException;
}
