package ua.golovchenko.artem.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InfoBaseTest {

    Info info;

    @Before
    public void init(){
        info = new InfoBase();
    }

    @Test
    public void testSetIdThenGetIdEquals(){
        info.setUser_id(1L);
        Long actualId = info.getUser_id();

        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetUserIdThenGetUserIdEquals(){
        info.setUser_id(1L);
        Long actualId = info.getUser_id();

        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetLevelIdThenGetLevelIdEquals() throws Exception {
        info.setLevel_id(1);
        Integer actualId = info.getLevel_id();

        assertEquals(new Integer(1),actualId);
    }
}