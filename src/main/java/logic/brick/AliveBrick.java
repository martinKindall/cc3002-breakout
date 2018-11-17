package logic.brick;

public class AliveBrick implements BrickStatus{

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public void reduceHit(AbstractBrick brick) {
        brick.reduceHit();
    }
}
