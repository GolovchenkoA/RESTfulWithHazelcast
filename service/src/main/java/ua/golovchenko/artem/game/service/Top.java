package ua.golovchenko.artem.game.service;

import java.util.Collection;

/**
 * Created by Artem on 13.08.2017.
 */
public interface Top<T, I, C> {
    public Collection<T> getTop(I id, C topCount) throws Exception;
}
