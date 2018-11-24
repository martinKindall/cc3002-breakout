package test;

import controller.Game;
import logic.level.AbstractLevel;
import logic.level.InvalidLevel;
import logic.level.Level;
import logic.level.PlayableLevel;

import org.junit.*;
import static org.junit.Assert.*;

public class LevelTest {
    private PlayableLevel level1;
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
        entryLevel = new InvalidLevel();
    }

    @Test
    public void addLevelTest(){
        assertFalse(entryLevel.hasNextLevel());

        level1.addPlayingLevel(level2);
        assertTrue(level1.hasNextLevel());
        assertFalse(level2.hasNextLevel());
    }

    @Test
    public void addLevelTestV2(){
        assertFalse(level1.hasNextLevel());
        assertFalse(level2.hasNextLevel());

        level2.addPlayingLevel(level1);
        assertTrue(level2.hasNextLevel());

        Level lastLevel = level2.getNextLevel();
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

    @Test
    public void attributesTest(){
        Level aLevel = new PlayableLevel("1er", 10, 1, 0, 0);

        assertEquals(aLevel.getName(), "1er");
        assertFalse(aLevel.getNextLevel().isPlayableLevel());

        Level otherLevel = new PlayableLevel("2nd", 10, 1, 0, 0);
        aLevel.setNextLevel(otherLevel);
        assertEquals(aLevel.getNextLevel(), otherLevel);

        assertEquals(aLevel.getNumberOfBricks(), 10);
        assertEquals(aLevel.getPoints(), 500);
    }


    @Test
    public void abstractTest(){
        AbstractLevel aLevel = new InvalidLevel();

        assertEquals(0, aLevel.getNumberOfBricks());
        assertEquals("", aLevel.getName());
        assertFalse(aLevel.isPlayableLevel());
        assertFalse(aLevel.hasNextLevel());

        Game aGame = new Game(3);

        assertEquals(0, aLevel.countObservers());
        aLevel.subscribe(aGame);
        assertEquals(1, aLevel.countObservers());

        aLevel.setNextLevel(level1);
        assertEquals(aLevel, aLevel.getNextLevel());

        assertEquals(0, aLevel.getBricks().size());

        assertEquals(0, aLevel.getPoints());
        aLevel.increaseScore(100);
        assertEquals(0, aLevel.getPoints());
    }
}
