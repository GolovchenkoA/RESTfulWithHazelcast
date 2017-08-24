package ua.golovchenko.artem.game.service;

import ua.golovchenko.artem.model.User;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public interface LevelService extends Top<User,Integer,Integer> {
    public void update(User user) throws Exception;
}
