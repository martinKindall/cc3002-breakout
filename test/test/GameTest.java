package test;

import controller.Game;
import logic.brick.Brick;

import logic.level.Level;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void setup(){
        game = new Game(3);
    }


    @Test
    public void gainBallTest(){
        Level level = game.newLevelWithBricksFull("level 1", 3, 1, 1, 0);
        game.setCurrentLevel(level);

        List<Brick> bricks = game.getBricks();

        for (Brick brick: bricks){
            brick.destroy();
        }

        assertEquals(game.getBalls(), 6);
    }

    @Test
    public void scoreTest(){
        int numberOfBricks = 10;

        Level level = game.newLevelWithBricksFull("level 1", numberOfBricks, 1, 0, 0);
        game.setCurrentLevel(level);

        List<Brick> bricks = game.getBricks();

        for (int i = 0; i < numberOfBricks/2; i++){
            bricks.get(i).destroy();
        }

        int obtainableScore = game.getLevelPoints();
        assertEquals(game.getCurrentPoints(), obtainableScore/2);

        for (int i = 0; i < numberOfBricks; i++){
            bricks.get(i).destroy();
        }

        assertEquals(game.getCurrentPoints(), obtainableScore);

        Level level2 = game.newLevelWithBricksFull("level 2", numberOfBricks, 1, 1, 0);
        game.setCurrentLevel(level2);

        bricks = game.getBricks();

        for (int i = 0; i < numberOfBricks*2; i++){  // destroying metal bricks too
            bricks.get(i).destroy();
        }

        assertEquals(game.getCurrentPoints(), obtainableScore*2);
    }

    @Test
    public void addingLevelsTest(){
        assertFalse(game.getCurrentLevel().isPlayableLevel());

        Level firstLevel = game.newLevelWithBricksFull("1er", 10, 1, 0, 0);
        game.setCurrentLevel(firstLevel);

        assertTrue(game.getCurrentLevel().isPlayableLevel());
        assertFalse(game.getCurrentLevel().getNextLevel().isPlayableLevel());
    }

    @Test
    public void advancingLevelsTest(){
        addingLevelsTest();

        assertFalse(game.winner());

        Level secondLev = game.newLevelWithBricksFull("2nd", 10, 1, 0, 0);
        assertNotEquals(secondLev, game.getCurrentLevel());
        game.addPlayingLevel(secondLev);
        assertNotEquals(secondLev, game.getCurrentLevel());

        List<Brick> bricks = game.getBricks();

        for (Brick brick : bricks){
            brick.destroy();
        }

        assertFalse(game.winner());
        assertEquals(secondLev, game.getCurrentLevel());

        bricks = game.getBricks();

        for (Brick brick : bricks){
            brick.destroy();
        }

        assertTrue(game.winner());
    }

    @Test
    public void goNextLevelTest(){
        addingLevelsTest();
        Level curr = game.getCurrentLevel();
        Level secondLev = game.newLevelWithBricksFull("2nd", 10, 1, 0, 0);
        game.addPlayingLevel(secondLev);

        assertEquals(curr, game.getCurrentLevel());

        game.goNextLevel();
        assertNotEquals(curr, game.getCurrentLevel());
        assertEquals(secondLev, game.getCurrentLevel());
    }
}
