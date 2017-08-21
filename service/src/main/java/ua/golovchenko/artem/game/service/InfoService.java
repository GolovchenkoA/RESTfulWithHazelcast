package ua.golovchenko.artem.game.service;

import ua.golovchenko.artem.model.Info;

import java.io.IOException;

/**
 * Created by Artem on 21.08.2017.
 *
 * @author Artem Golovchenko
 */
public interface InfoService {
    public void add(Info item) throws IOException;
}
