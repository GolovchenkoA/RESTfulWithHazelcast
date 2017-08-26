package ua.golovchenko.artem.game.cache;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.golovchenko.artem.game.service.LevelService;
import ua.golovchenko.artem.model.ResultBase;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.util.ArrayList;
import java.util.List;

@Ignore
public class CacheLevelServiceTest {
    private LevelService levelService;


    @BeforeClass
    public void setUp(){
        levelService = new CacheLevelService();
    }


    @Test
    public void testGetTop() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    //Additional methods
    public List<User> getUnsortedUsersList() {
        List<User> users = new ArrayList<>();
        User user1 = new UserBase(1L,"email1@com.ua","name1","nick1");
        User user2 = new UserBase(2L,"email2@com.ua","name2","nick2");
        User user3 = new UserBase(3L,"email3@com.ua","name3","nick3");

        user1.getResults().add(new ResultBase(user1.getUser_id(),1,100));
        user2.getResults().add(new ResultBase(user2.getUser_id(),1,50));
        user3.getResults().add(new ResultBase(user3.getUser_id(),1,10));

        users.add(user2);
        users.add(user1);
        users.add(user3);

        return users;
    }

    public List<User> getSortedUsersListInDescending() {
        List<User> users = new ArrayList<>();
        User user1 = new UserBase(1L,"email1@com.ua","name1","nick1");
        User user2 = new UserBase(2L,"email2@com.ua","name2","nick2");
        User user3 = new UserBase(3L,"email3@com.ua","name3","nick3");

        user1.getResults().add(new ResultBase(user1.getUser_id(),1,100));
        user2.getResults().add(new ResultBase(user2.getUser_id(),1,50));
        user3.getResults().add(new ResultBase(user3.getUser_id(),1,10));

        users.add(user3);
        users.add(user2);
        users.add(user1);

        return users;
    }


}