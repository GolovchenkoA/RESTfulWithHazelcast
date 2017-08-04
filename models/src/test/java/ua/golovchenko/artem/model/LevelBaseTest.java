package ua.golovchenko.artem.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelBaseTest {

    private Level level;

    @Before
    public void init(){
        level = new LevelBase();
    }

    @Test
    public void testSetIdThenGetIdEquals() throws Exception {
        level.setId(1);
        Integer actualId = level.getId();

        assertEquals(new Integer(1),actualId);
    }

    @Test
    public void testSetDescriptionThenDescriptionEquals() throws Exception {
        String description = "Level Description";
        level.setDescription(description);

        assertEquals(description, level.getDescription());

    }
}