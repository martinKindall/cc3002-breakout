package logic.brick;

import logic.level.AbstractLevel;

public class AbstractBrick implements Brick{

    protected int remainingHits;
    protected int score;


    public AbstractBrick(int score, int remainingHits){
        this.score = score;
        this.remainingHits = remainingHits;
    }


    @Override
    public void hit() {
        remainingHits--;

        if (remainingHits == 0){
            // notificar ladrillo destruido
        }
    }

    @Override
    public boolean isDestroyed() {
        return remainingHits == 0;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public int remainingHits() {
        return remainingHits;
    }
}
