package test;

import controller.Game;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;

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
        game.newLevelWithBricksFull("level 1", 3, 1, 1, 0);
        game.goNextLevel();
        List<Brick> bricks = game.getBricks();

        for (Brick brick: bricks){
            brick.destroy();
        }

        assertEquals(game.getBalls(), 6);
    }

    @Test
    public void scoreTest(){
        int numberOfBricks = 10;
        game.newLevelWithBricksFull("level 1", numberOfBricks, 1, 1, 0);
        game.goNextLevel();
        List<Brick> bricks = game.getBricks();

        for (int i = 0; i < numberOfBricks/2; i++){
            bricks.get(i).destroy();
        }

        assertEquals(game.getBalls(), 6);
    }
}
