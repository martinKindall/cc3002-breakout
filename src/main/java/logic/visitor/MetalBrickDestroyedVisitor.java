package logic.visitor;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a visitor when a metal brick is destroyed
 */

import controller.Game;
import logic.brick.Brick;

public class MetalBrickDestroyedVisitor extends AbstractDestroyedBrickVisitor {

    /**
     * Reuses parent's constructor
     */
    public MetalBrickDestroyedVisitor(Brick brick){
        super(brick);
    }

    /**
     * Reuses parent's visitor logic (increases score).
     * Metal bricks score is 0 anyways.
     * But it increases game's balls by 1.
     * @param game current game
     */
    @Override
    public void visitGame(Game game){
        super.visitGame(game);
        game.increaseBalls();
    }
}
