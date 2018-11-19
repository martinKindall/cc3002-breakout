package logic.brick;

public class MetalBrick extends AbstractBrick {

    public MetalBrick() {
        super(0, 10);
    }


    @Override
    protected void destroyedNotification() {
        setChanged();
        notifyObservers(new MetalBrickDestroyed(this));
    }
}
