package logic.brick;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a destroyed brick
 */

public class DestroyedBrick implements BrickStatus{

    /**
     * Returns true because brick is destroyed
     */
    @Override
    public boolean isDestroyed() {
        return true;
    }

    /**
     * Brick remains idle because it is destroyed
     * @param brick the brick being hit
     */
    @Override
    public void reduceHit(AbstractBrick brick) {

    }
}
