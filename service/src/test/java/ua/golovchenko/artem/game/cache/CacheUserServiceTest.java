package ua.golovchenko.artem.game.cache;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import ua.golovchenko.artem.game.cache.dao.BaseUserDAO;
import ua.golovchenko.artem.game.service.UserService;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CacheUserServiceTest {
    private UserService userService;
    private BaseUserDAO userDAO;
    private User user;
    private Long userId;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        userDAO = mock(BaseUserDAO.class);
        userService = new CacheUserService(userDAO);
        userId = 1L;
        this.user = new UserBase("email@com.ua","name", "nick");
        this.user.setUser_id(userId);
    }

    @Test
    public void testGetUserById() throws Exception {
        Long userId = 1L;
        when(userDAO.get(userId)).thenReturn(user);
        User newUser = userService.get(userId);
        assertEquals(user, newUser);
    }

    @Test
    public void testGetUserByIdThenThrowExceptionIfUserDoesNotExists() throws Exception {
        exception.expect(Exception.class);
        doNothing().when(userDAO).findAll();
        doThrow(new NullPointerException()).when(userDAO).get(1L);
        userService.get(1L);
    }

    @Test
    public void testUserServiceMethodAddMustCallUserDaoMethodAdd() throws Exception {
        userService.add(user);
        verify(userDAO, times(1)).add(user);
    }

    @Test
    public void testUserServiceAddMustThrowExceptionWhenUserDaoThrowException() throws Exception {
        exception.expect(Exception.class);
        doThrow(new Exception()).when(userDAO).add(user);
        this.userService.add(user);
    }

    @Test
    public void testDeleteNotImplemented() throws Exception {
        exception.expect(NotImplementedException.class);
        this.userService.delete(1L);
    }

    @Test
    public void testFindAllUserServiceAndFindAllUserDaoMustBeEquals() throws Exception {
        Map<Long,User> users = this.generateUsersMap();
        when(this.userDAO.findAll()).thenReturn(users);
        assertEquals(users, this.userService.findAll());
    }

    @Test
    public void testFindAllUserServiceThrowExceptionIfUserDaoThrowException() throws Exception {
        exception.expect(Exception.class);
        when(this.userDAO.findAll()).thenThrow(new Exception());
        this.userService.findAll();
    }

    @Test
    public void testuUserServiceUpdateMustCallUserDaoUpdate() throws Exception {
        userService.update(user);
        verify(userDAO, times(1)).update(user);
    }

    @Test
    public void testuUserServiceUpdateMustThrowExceptionWhenUserDaoUpdateThrowException() throws Exception {
        exception.expect(Exception.class);
        doThrow(new Exception()).when(userDAO).update(user);
        this.userService.update(user);
    }



    // Additional methods

    private Map generateUsersMap() {
        Map<Long,User> users = new HashMap<>();
        users.put(1L,new UserBase(1L,"email1@com.ua","name1","nick1"));
        users.put(2L,new UserBase(2L,"email2@com.ua","name2","nick2"));
        return users;
    }
}