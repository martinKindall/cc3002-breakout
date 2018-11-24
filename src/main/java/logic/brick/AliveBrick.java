package logic.brick;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of an alive brick
 */

public class AliveBrick implements BrickStatus{

    /**
     * Returns false because brick is alive
     */
    @Override
    public boolean isDestroyed() {
        return false;
    }

    /**
     * calls AbstractBrick method reduceHit,
     * because it is still alive and able to
     * receive damage
     */
    @Override
    public void reduceHit(AbstractBrick brick) {
        brick.reduceHit();
    }
}
