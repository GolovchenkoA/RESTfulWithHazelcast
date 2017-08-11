package ua.golovchenko.artem.web;

import ua.golovchenko.artem.model.User;

import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 */
public interface UsersManager<T> {
    public T getUser(Long id);
    public void addUser(T user);
    public boolean deleteUser(Long id);
    public boolean updateUser(Long id);
    public Map<Long,T> findAll();
}
