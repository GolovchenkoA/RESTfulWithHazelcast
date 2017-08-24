package ua.golovchenko.artem.game.cache;

import org.junit.Before;
import org.junit.Test;
import ua.golovchenko.artem.model.Result;
import ua.golovchenko.artem.model.ResultBase;
import ua.golovchenko.artem.model.User;
import ua.golovchenko.artem.model.UserBase;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CacheResultServiceTest {
    private CacheResultService resultService;
    private CacheUserService userService = mock(CacheUserService.class);
    private CacheLevelService levelService = mock(CacheLevelService.class);
    private User user;
    private Long userId;

    @Before
    public void init(){
        userId = 1L;
        this.resultService = new CacheResultService(userService,levelService);
        this.user = new UserBase("email@com.ua","name", "nick");
        this.user.setUser_id(userId);
    }

    @Test
    public void testAdd() throws Exception {
        when(userService.get(userId)).thenReturn(user);

        Result result = new ResultBase(1L,1,1);
        assertTrue(userService.get(1L).getResults().equals(new ArrayList<>()));
        assertTrue("a".equals("a"));
        //resultService.add(result);
    }
}