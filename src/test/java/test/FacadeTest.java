package test;

import facade.HomeworkTwoFacade;

import logic.level.Level;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author https://github.com/martinKindall
 */

public class FacadeTest {
    private HomeworkTwoFacade facade;

    @Before
    public void setup(){
        facade = new HomeworkTwoFacade();
    }


    @Test
    public void playability(){
        assertFalse(facade.winner());
        assertEquals(0, facade.getCurrentPoints());
        assertEquals(0, facade.getLevelPoints());

        assertEquals(0, facade.getBricks().size());
        assertEquals(0, facade.numberOfBricks());

        Level first = facade.newLevelWithBricksNoMetal("uno", 10, 1, 0);
        facade.setCurrentLevel(first);

        assertEquals(10, facade.getBricks().size());
        assertEquals(10, facade.numberOfBricks());
        assertEquals(500, facade.getLevelPoints());

        assertEquals(3, facade.getBallsLeft());
        facade.dropBall();
        assertEquals(2, facade.getBallsLeft());

        assertFalse(facade.isGameOver());

        facade.dropBall();
        facade.dropBall();

        assertTrue(facade.isGameOver());

    }
}
