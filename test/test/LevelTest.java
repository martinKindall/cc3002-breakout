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

    @Test
    public void addLevelTestV2(){
        entryLevel.addPlayingLevel(level2);

        assertFalse(level1.hasNextLevel());
        assertFalse(level2.hasNextLevel());

        level2.addPlayingLevel(level1);
        assertTrue(level2.hasNextLevel());

        Level lastLevel = entryLevel.getNextLevel().getNextLevel();
        assertEquals(lastLevel, level1);
    }


    @Test
    public void playableTest(){
        assertFalse(entryLevel.isPlayableLevel());
        assertTrue(level1.isPlayableLevel());
        assertFalse(new InvalidLevel().isPlayableLevel());
    }
}
