package ua.golovchenko.artem.game.dao;

import ua.golovchenko.artem.model.User;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Artem on 20.08.2017.
 *
 * @author Artem Golovchenko
 */
public class UserMapStoreImpl implements UserMapStore {

    @Override
    public void store(Long aLong, User user) {

    }

    @Override
    public void storeAll(Map<Long, User> map) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void deleteAll(Collection<Long> collection) {

    }

    @Override
    public User load(Long aLong) {
        return null;
    }

    @Override
    public Map<Long, User> loadAll(Collection<Long> collection) {
        return null;
    }

    @Override
    public Iterable<Long> loadAllKeys() {
        return null;
    }
}
