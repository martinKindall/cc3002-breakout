package logic.brick;

/**
 * @author https://github.com/martinKindall
 *
 * This abstract class defines the behaviour
 * of a default brick
 */

import logic.visitor.CommonBrickDestroyedVisitor;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBrick extends Observable implements Brick{

    private int remainingHits;
    private int score;
    private BrickStatus status;

    /**
     * Every brick is created alive by default
     * @param score obtainable score when brick is destroyed
     * @param remainingHits hits left in order to destroy
     */
    AbstractBrick(int score, int remainingHits){
        this.score = score;
        this.remainingHits = remainingHits;
        this.status = new AliveBrick();
    }

    /**
     * Reduces a remaining hit on the brick depending
     * on its status
     */
    @Override
    public void hit() {
        this.status.reduceHit(this);
    }

    /**
     * Returns true or false depending on its status
     * @return true if it is destroyed
     */
    @Override
    public boolean isDestroyed() {
        return this.status.isDestroyed();
    }

    /**
     * Returns the amount of score of the brick
     * @return amount of score obtainable
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Obtains the remaining hits of the brick.
     * It is always a positive or zero amount
     * @return hits left
     */
    @Override
    public int remainingHits() {
        return remainingHits;
    }

    /**
     * Adds an observer to the brick.
     * @param observer the one observing the brick
     */
    @Override
    public void subscribe(Observer observer) {
        addObserver(observer);
    }

    /**
     * Helper method, simulated a brick being damaged hit by hit until it is destroyed.
     */
    @Override
    public void destroy(){
        while(!isDestroyed()){
            hit();
        }
    }

    @Override
    public boolean isWooden(){
        return false;
    }

    @Override
    public boolean isGlass(){
        return false;
    }

    @Override
    public boolean isMetal(){
        return false;
    }

    protected void reduceHit(){
        remainingHits--;

        if (remainingHits == 0){
            status = new DestroyedBrick();
            this.destroyedNotification();
        }
    }

    protected void destroyedNotification() {
        setChanged();
        notifyObservers(new CommonBrickDestroyedVisitor(this));
    }
}
