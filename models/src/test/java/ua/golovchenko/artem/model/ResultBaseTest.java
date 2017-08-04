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
        result.setId(1L);
        Long actualId = result.getId();

        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetResultThenGetResultEquals() {
        String description = "Level Description";
        result.setResult(description);

        assertEquals(description, result.getResult());
    }

    @Test
    public void testSetUserIdThenGetUserIdEquals(){
        result.setUserId(1L);
        Long actualId = result.getUserId();

        assertEquals(Long.valueOf(1L),actualId);
    }

    @Test
    public void testSetLevelIdThenGetLevelIdEquals() throws Exception {
        result.setLevelId(1);
        Integer actualId = result.getLevelId();

        assertEquals(new Integer(1),actualId);
    }
}