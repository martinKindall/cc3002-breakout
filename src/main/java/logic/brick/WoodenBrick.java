package logic.brick;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a MetalBrick
 */

public class WoodenBrick extends AbstractBrick {

    /**
     * Its score is 200 and endures 3 hits
     */
    public WoodenBrick() {
        super(200, 3);
    }

    @Override
    public boolean isWooden(){
        return true;
    }
}
