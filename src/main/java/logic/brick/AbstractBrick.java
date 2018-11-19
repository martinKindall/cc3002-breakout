package logic.brick;

import controller.Game;
import logic.level.AbstractLevel;
import logic.level.Level;
import logic.level.PlayableLevel;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBrick extends Observable implements Brick{

    private int remainingHits;
    private int score;
    private BrickStatus status;
    private int extraBalls;


    AbstractBrick(int score, int remainingHits){
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

    void reduceHit(){
        remainingHits--;

        if (remainingHits == 0){
            status = new DestroyedBrick();
            this.destroyedNotification();
        }
    }

    protected void destroyedNotification() {
        setChanged();
        notifyObservers(this);
    }

    public void subscribe(Observer observer) {
        addObserver(observer);
    }

    @Override
    public void destroy(){
        while(!isDestroyed()){
            hit();
        }
    }

    @Override
    public void acceptGame(Game game){
        game.increaseScore(score);
    }

    public void acceptLevel(Level level){
        level.increaseScore(score);
    }
}
