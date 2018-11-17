package logic.brick;

import logic.level.AbstractLevel;

public class AbstractBrick implements Brick{

    protected int remainingHits;
    protected int score;
    protected BrickStatus status;


    public AbstractBrick(int score, int remainingHits){
        this.score = score;
        this.remainingHits = remainingHits;
        this.status = new AliveBrick();
    }

    @Override
    public void hit() {
        this.status.reduceHit(this);
    }

    @Override
    public boolean isDestroyed() {
        return this.status.isDestroyed();
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public int remainingHits() {
        return remainingHits;
    }

    public void reduceHit(){
        remainingHits--;

        if (remainingHits == 0){
            status = new DestroyedBrick();
        }
    }
}
