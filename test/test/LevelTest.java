package test;

import logic.level.EntryLevel;
import logic.level.InvalidLevel;
import logic.level.Level;

import logic.level.PlayableLevel;
import org.junit.*;
import static org.junit.Assert.*;

public class LevelTest {
    private Level level1;
    private Level level2;
    private Level entryLevel;


    @Before
    public void setUp(){
        level1 = new PlayableLevel("level1");
        level2 = new PlayableLevel("level2");
        entryLevel = new EntryLevel();
    }

    @Test
    public void addLevelTest(){
        assertFalse(entryLevel.hasNextLevel());

        entryLevel.addPlayingLevel(level1);
        assertTrue(entryLevel.hasNextLevel());
        assertEquals(level1, entryLevel.getNextLevel());

        Level nextLevel = entryLevel.getNextLevel();
        assertFalse(nextLevel.hasNextLevel());

        entryLevel.addPlayingLevel(level2);
        assertTrue(level1.hasNextLevel());
        assertFalse(level2.hasNextLevel());
    }
}
