package logic.brick;

import logic.visitor.MetalBrickDestroyedVisitor;

public class MetalBrick extends AbstractBrick {

    public MetalBrick() {
        super(0, 10);
    }


    @Override
    protected void destroyedNotification() {
        setChanged();
        notifyObservers(new MetalBrickDestroyedVisitor(this));
    }
}
