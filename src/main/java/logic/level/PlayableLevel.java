package logic.level;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a playable level
 */

import logic.brick.*;
import logic.visitor.FinishedLevelVisitor;
import logic.visitor.Visitor;

import java.util.*;

public class PlayableLevel extends AbstractLevel implements Observer {

    protected List<Brick> bricks;
    private int obtainableScore;
    private int currentScore;
    private int numberOfBricks;


    /**
     * Any playable is initialized with the following parameters
     * @param nombre name of the level
     * @param numberOfBricks initial number of bricks (can be surpassed if metal bricks are added)
     * @param probOfGlass the chance that a brick turns into wooden brick or glass brick
     * @param probOfMetal the chance that a metal brick is generated
     * @param seed defines the random sequence, useful to test.
     */
    public PlayableLevel(String nombre, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        super(nombre);
        this.numberOfBricks = numberOfBricks;
        currentScore = 0;
        obtainableScore = 0;

        bricks = new LinkedList<>();
        Brick newBrick;

        Random generator = new Random(seed);

        for (int i=0; i < numberOfBricks; i++){
            double coin = generator.nextDouble();
            if (coin <= probOfGlass){
                newBrick = new GlassBrick();
            }
            else{
                newBrick = new WoodenBrick();
            }

            bricks.add(newBrick);
            obtainableScore += newBrick.getScore();
            newBrick.subscribe(this);
        }

        for (int i=0; i < numberOfBricks; i++){
            double coin = generator.nextDouble();
            if (coin <= probOfMetal){
                newBrick = new MetalBrick();
                bricks.add(newBrick);
                obtainableScore += newBrick.getScore();
                this.numberOfBricks++;
                newBrick.subscribe(this);
            }
        }
    }

    /**
     * Obtains a list of bricks of the level
     * @return list of bricks, default is an empty list
     */
    @Override
    public List<Brick> getBricks() {
        return bricks;
    }

    /**
     * Obtains number of bricks
     */
    @Override
    public int getNumberOfBricks(){
        return numberOfBricks;
    }

    /** Returns true if it is a playable level
     */
    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    /**
     * @return Returns true if next level is playable
     */
    @Override
    public boolean hasNextLevel() {
        return next.isPlayableLevel();
    }

    /**
     * Sets next level to the specified level
     * @param level the next level of a level object
     */
    @Override
    public void setNextLevel(Level level) {
        next = level;
    }

    /**
     * @return number of obtainable points, corresponding
     * to the sum of bricks score.
     */
    @Override
    public int getPoints() {
        return obtainableScore;
    }

    /**
     * Overrides the observer's method update.
     * It receives any notificacion that happened to
     * an element of the game, i.e. a brick
     * It receives a visitor to execute the desired logic.
     * @param o object that is being observed
     * @param arg message sent by the observed object
     */
    @Override
    public void update(Observable o, Object arg) {
        ((Visitor) arg).visitLevel(this);
    }

    /**
     * Increases level current score.
     * If current score equals the obtainable score,
     * then the level is finished and a notification is sent.
     * @param score amount of score being added
     */
    @Override
    public void increaseScore(int score){
        currentScore += score;

        if (currentScore == obtainableScore){
            levelComplete();
        }
    }

    /**
     * Decreases by 1 the current amount of bricks.
     */
    public void decreaseBricks(){
        --numberOfBricks;
    }

    /**
     * Notifies the observer that level is finished,
     * creates a proper visitor
     */
    private void levelComplete() {
        setChanged();
        notifyObservers(new FinishedLevelVisitor());
    }
}
