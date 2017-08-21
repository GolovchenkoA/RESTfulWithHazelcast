package ua.golovchenko.artem.model;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;


public class UserBaseTest{
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String USER_NAME_PATTERN = "[A-Za-z]+";

    private User user;

    @Before
    public void init(){
        user = new UserBase();
    }

    @Test
    public void testSetIdThenGetIdEquals() throws Exception {
        user.setUser_id(1L);
        Long actualId = user.getUser_id();

        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetEmailThenGetEmailEquals() throws Exception {
        String email = "user@domain.com";
        user.setEmail(email);

        assertEquals(email, user.getEmail());
    }


    @Test
    public void testSetEmailThenGetEmailSeccessful() throws Exception {

        String email = "user@domain.com";
        user.setEmail(email);

        assertTrue(validateEmail(user.getEmail(), EMAIL_PATTERN));

    }

    @Test
    public void testSetEmailThenGetEmailFail() throws Exception {
        String email = "badEmail";
        user.setEmail(email);

        User user2 = new UserBase();
        String email2 = "badEmail.ua";
        user2.setEmail(email2);

        assertFalse(validateEmail(user.getEmail(), EMAIL_PATTERN));
        assertFalse(validateEmail(user2.getEmail(), EMAIL_PATTERN));

    }

    @Test
    public void testSetNameThenGetNameEquals() throws Exception {
        String name = "User";
        user.setName(name);

        assertEquals(name, user.getName());
    }

    @Test
    public void testSetNameThenGetNameFail() throws Exception {
        String name = "User2";
        user.setName(name);

        assertFalse(user.getName().matches(USER_NAME_PATTERN));
    }

    public void testSetNickThenGetNickFail() throws Exception {

    }

    @Test
    public void testSetNickThenGetNickSeccessful() throws Exception {
        String name = "User";
        user.setName(name);

        User user2 = new UserBase();
        String name2 = "user";
        user2.setName(name2);

        assertTrue(user.getName().matches(USER_NAME_PATTERN));
        assertTrue(user2.getName().matches(USER_NAME_PATTERN));
    }

    private boolean validateEmail(final String email, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}