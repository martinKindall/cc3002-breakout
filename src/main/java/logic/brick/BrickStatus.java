package logic.brick;

/**
 * @author https://github.com/martinKindall
 *
 * This class describes whether the brick is alive or not
 * Useful to redefine the following method depending on its status
 */

public interface BrickStatus {
    /**
     * Returns true if the brick has no remaining hits
     * @return
     */
    boolean isDestroyed();

    /**
     * Reduces brick's remaining hits by 1.
     * @param brick the brick being hit
     */
    void reduceHit(AbstractBrick brick);
}
