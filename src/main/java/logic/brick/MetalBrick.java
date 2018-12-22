package logic.brick;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a MetalBrick
 */

import logic.visitor.MetalBrickDestroyedVisitor;

public class MetalBrick extends AbstractBrick {

    /**
     * Its has 0 score and endures 10 hits
     */
    public MetalBrick() {
        super(0, 1);
    }


    /**
     * Whenever a metal brick is destroyed,
     * it launches an special visitor MetalBrickDestroyedVisitor that
     * triggers an action on Game
     */
    @Override
    protected void destroyedNotification() {
        setChanged();
        notifyObservers(new MetalBrickDestroyedVisitor(this));
    }

    @Override
    public boolean isMetal(){
        return true;
    }
}
