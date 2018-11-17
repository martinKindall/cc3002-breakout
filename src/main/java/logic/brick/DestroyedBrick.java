package logic.brick;

public class DestroyedBrick implements BrickStatus{
    @Override
    public boolean isDestroyed() {
        return true;
    }

    @Override
    public void reduceHit(AbstractBrick brick) {

    }
}
