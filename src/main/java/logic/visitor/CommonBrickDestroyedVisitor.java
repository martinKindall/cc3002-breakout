package logic.visitor;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a visitor when a generic brick is destroyed
 */

import logic.brick.Brick;

public class CommonBrickDestroyedVisitor extends AbstractDestroyedBrickVisitor {

    /**
     * Reuses the parent's constructor
     */
    public CommonBrickDestroyedVisitor(Brick brick){
        super(brick);
    }
}
