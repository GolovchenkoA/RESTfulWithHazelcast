package ua.golovchenko.artem.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResultBaseTest {

    Result result;

    @Before
    public void init(){
        result = new ResultBase();
    }

    @Test
    public void testSetIdThenGetIdEquals(){
        result.setUser_id(1L);
        Long actualId = result.getUser_id();

        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetUserIdThenGetUserIdEquals(){
        result.setUser_id(1L);
        Long actualId = result.getUser_id();

        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetLevelIdThenGetLevelIdEquals() throws Exception {
        result.setLevel_id(1);
        Integer actualId = result.getLevel_id();

        assertEquals(new Integer(1),actualId);
    }
}