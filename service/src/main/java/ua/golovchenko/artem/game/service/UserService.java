package ua.golovchenko.artem.game.service;

import ua.golovchenko.artem.game.dao.CRUD;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.User;

/**
 * Created by Artem on 20.08.2017.
 *
 * @author Artem Golovchenko
 */
public interface UserService extends CRUD<User>,Top<Result,Long,Long> {
    User generateNewUser(Long id);
}
