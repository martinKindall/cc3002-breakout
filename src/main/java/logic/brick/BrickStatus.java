package logic.brick;

public interface BrickStatus {
    boolean isDestroyed();
    void reduceHit(AbstractBrick brick);
}
