package ua.golovchenko.artem.game.service;

import ua.golovchenko.artem.model.User;

import java.util.List;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public interface LevelService {
    public List<User> get(Integer level , Integer topCount) throws Exception;
}
