package logic.level;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a default Level
 */

import logic.brick.Brick;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public abstract class AbstractLevel extends Observable implements Level {

    protected String name;
    protected Level next;

    /**
     * default name is ""
     */
    public AbstractLevel(){
        name = "";
    }

    /**
     * Every level has a name, next default level
     * is an invalid level
     * @param nombre
     */
    public AbstractLevel(String nombre){
        name = nombre;
        next = new InvalidLevel();
    }

    /**
     * Obtains level's name
     * @return string name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Obtains number of bricks, default is 0
     * @return zero bricks
     */
    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    /**
     * Obtains the next level
     * @return pointer to next level
     */
    @Override
    public Level getNextLevel() {
        return next;
    }

    /** Returns true if it is a playable level
     * default is false
     * @return default is false
     */
    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    /**
     * @return Returns true if next level is playable
     */
    @Override
    public boolean hasNextLevel() {
        return next.isPlayableLevel();
    }

    /**
     * @return number of obtainable points, corresponding
     * to the sum of bricks score. Default is 0.
     */
    @Override
    public int getPoints() {
        return 0;
    }

    /**
     * Adds a level in the end of the level list.
     * Recursively asks the next level to add the new level.
     * @param level the level to be added
     * @return pointer to current level
     */
    @Override
    public Level addPlayingLevel(Level level) {
        next = next.addPlayingLevel(level);
        return this;
    }

    /**
     * Sets next level to the specified level
     * @param level the next level of a level object
     */
    @Override
    public void setNextLevel(Level level){}

    /**
     * Obtains a list of bricks of the level
     * @return list of bricks, default is an empty list
     */
    @Override
    public List<Brick> getBricks() {
        return new LinkedList<>();
    }

    /**
     * Adds an observer to the current level
     * @param observer the observer watching the level
     */
    @Override
    public void subscribe(Observer observer) {
        addObserver(observer);
    }

    /**
     * Increases level current score.
     * By default this method does nothing.
     * @param score amount of score being added
     */
    @Override
    public void increaseScore(int score) {}
}
