package ua.golovchenko.artem.game.cache;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ua.golovchenko.artem.game.cache.dao.BaseUserDAO;
import ua.golovchenko.artem.game.dao.UserDAO;
import ua.golovchenko.artem.model.ResultBase;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class CacheResultServiceTest {
    private CacheResultService resultService;
    private CacheUserService userService;
    private CacheLevelService levelService = mock(CacheLevelService.class);
    private UserDAO userDAO = mock(BaseUserDAO.class);
    private User user;
    private Long userId;

    @Before
    public void init(){
        userService = spy(new CacheUserService(userDAO));
        userId = 1L;
        this.resultService = new CacheResultService(userService,levelService);
        this.user = new UserBase("email@com.ua","name", "nick");
        this.user.setUser_id(userId);
    }

    @Test
    public void testConstructorByDefault(){
        CacheResultService resultService = new CacheResultService();
        assertNotNull(resultService.getLevelService());
        assertNotNull(resultService.getUserService());
    }

    @Test
    public void testCheckMaximumNumberOfResultsAllowedMustRemove() throws Exception {
        int maximum_result_numbers = 2;
        int must_be_removed_count = 2;
        user.getResults().add(new ResultBase(user.getUser_id(),1,1));
        user.getResults().add(new ResultBase(user.getUser_id(),1,2));
        user.getResults().add(new ResultBase(user.getUser_id(),1,3));
        user.getResults().add(new ResultBase(user.getUser_id(),1,4));

        int removed_items_count = resultService.checkMaximumNumberOfResultsAllowed(user,maximum_result_numbers);

        assertEquals(must_be_removed_count, removed_items_count);
    }

    @Test
    public void testCheckMaximumNumberOfResultsAllowedNotRemove() throws Exception {
        int maximum_result_numbers = 2;
        int must_be_removed_count = 0;
        user.getResults().add(new ResultBase(user.getUser_id(),1,1));
        user.getResults().add(new ResultBase(user.getUser_id(),1,2));

        int removed_items_count = resultService.checkMaximumNumberOfResultsAllowed(user,maximum_result_numbers);

        assertEquals(must_be_removed_count, removed_items_count);
    }


    @Test
    public void testCreateUserIfNotExistsMustCreateNewUser() throws Exception {
        Long user_id = 1L;
        when(userService.generateNewUser(anyLong())).thenReturn(user);
        User created_user = resultService.createUserIfNotExists(null,user_id);

        assertNotNull(created_user);
        assertEquals(user, created_user);
    }

    @Test
    public void testCreateUserIfNotExistsSkipCreateNewUser() throws Exception {
        resultService.createUserIfNotExists(user,user.getUser_id());

        verify(userService, never()).generateNewUser(anyLong());
        assertEquals(user, user);
    }


    @Ignore("break 28.08.2017")
    @Test
    public void testAddResult() throws Exception {
        User user = new UserBase(1L,"user@email.com","name","nick");
        user.getResults().add(new ResultBase(user.getUser_id(), 1, 1));

        when(userDAO.get(user.getUser_id())).thenReturn(user);
        resultService.add(new ResultBase(user.getUser_id(),1,2));

        assertEquals(2,user.getResults().size());
        verify(userService,times(1)).update(user);
        verify(levelService,times(1)).update(user);
    }
}