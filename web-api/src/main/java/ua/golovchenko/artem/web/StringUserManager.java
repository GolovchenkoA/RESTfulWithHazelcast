package ua.golovchenko.artem.web;

import ua.golovchenko.artem.model.User;

/**
 * Created by Artem on 11.08.2017.
 */
public class StringUserManager implements UsersManager {

    private static UsersManager<User> users;


    StringUserManager(BaseUserManager baseUserManager){
       users = new BaseUserManager();
    }


}
