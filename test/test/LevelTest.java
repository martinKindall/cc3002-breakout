package test;

import controller.Game;
import logic.level.EntryLevel;
import logic.level.InvalidLevel;
import logic.level.Level;
import logic.level.PlayableLevel;

import org.junit.*;
import static org.junit.Assert.*;

public class LevelTest {
    private Level level1;
    private Level level2;
    private Level level3;
    private Level level4;
    private Level level5;
    private Level level6;
    private Level entryLevel;


    @Before
    public void setUp(){
        int seed = 3;

        level1 = new PlayableLevel("level1", 10, 0, 0, seed);
        level2 = new PlayableLevel("level2", 10, 1, 0, seed);
        level3 = new PlayableLevel("level3", 10, 0.4, 0, seed);
        level4 = new PlayableLevel("level4", 10, 0.7, 0, seed);
        level5 = new PlayableLevel("level5", 10, 1, 1, seed);
        level6 = new PlayableLevel("level6", 10, 1, 0.6, seed);
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

    @Test
    public void brickListTest(){
        assertEquals(level5.getBricks().size(), 20);
        assertEquals(level6.getBricks().size(), 17);
    }


    @Test
    public void scoreTest(){
        assertEquals(level1.getPoints(), 2000);
        assertEquals(level2.getPoints(), 500);
        assertEquals(level3.getPoints(), 1400);
        assertEquals(level4.getPoints(), 1250);
    }
}
