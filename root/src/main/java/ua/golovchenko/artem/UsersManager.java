package ua.golovchenko.artem;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Artem on 11.08.2017.
 *
 * @author Golovchenko Artem
 * The interface for working with the interface User
 */
public interface UsersManager<T> {
    public T getUser(Long id);
    public void addUser(T user) throws IOException;
    public boolean deleteUser(Long id);
    public boolean updateUser(Long id);
    public Map<Long,T> findAll() throws IOException;
}
