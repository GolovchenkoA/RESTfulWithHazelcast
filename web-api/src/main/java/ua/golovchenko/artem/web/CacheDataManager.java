package ua.golovchenko.artem.web;

import ua.golovchenko.artem.model.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Artem on 11.08.2017.
 */
public class CacheDataManager {
    private Map<Long, User> users = new ConcurrentHashMap<>();

    public static void CacheDataManager(){

    }
}
