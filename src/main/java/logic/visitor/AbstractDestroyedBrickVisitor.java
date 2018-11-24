package logic.visitor;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a visitor when a generic brick is destroyed
 */

import controller.Game;
import logic.brick.Brick;
import logic.level.PlayableLevel;

public class AbstractDestroyedBrickVisitor implements Visitor{
    private Brick destroyedBrick;

    /**
     * A visitor stores a reference to the destroyed brick
     * @param brick destroyed brick
     */
    public AbstractDestroyedBrickVisitor(Brick brick){
        destroyedBrick = brick;
    }

    /**
     * Increases game score by an amount of the destroyed brick score
     * @param game current game
     */
    @Override
    public void visitGame(Game game){
        game.increaseScore(destroyedBrick.getScore());
    }

    /**
     * Increases level score by an amount of the destroyed brick score.
     * Decreases level's amount of bricks by 1.
     * @param level current level
     */
    @Override
    public void visitLevel(PlayableLevel level){
        level.increaseScore(destroyedBrick.getScore());
        level.decreaseBricks();
    }
}
