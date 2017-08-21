package ua.golovchenko.artem.game.dao;

import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 *
 * @author Golovchenko Artem
 * The interface for working with the interface User
 */
public interface CRUD<T> {
    public T get(Long id);
    public void add(T obj) throws Exception;
    public boolean delete(Long id);
    public Map<Long,T> findAll() throws Exception;
}
