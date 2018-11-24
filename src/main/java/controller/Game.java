package controller;

/**
 * @author Juan-Pablo Silva
 * @author https://github.com/martinKindall
 *
 * This is the controller of the logic packages. This class describes the
 * behaviour of the game.
 */

import logic.brick.Brick;
import logic.level.InvalidLevel;
import logic.level.Level;
import logic.level.PlayableLevel;
import logic.visitor.Visitor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 */
public class Game implements Observer {
    private Level currLevel;
    private int balls;
    private int currScore;
    private boolean finished;

    /**
     * Every game starts with a number of balls,
     * which are equivalent to the player's lives
     * Initial score is 0, and the first
     * level is not a valid level.
     * @param balls initial balls
     */
    public Game(int balls) {
        this.balls = balls;
        currLevel = new InvalidLevel();
        currScore = 0;
        finished = false;
    }

    /**
     * increases the number of balls by 1
     */
    public void increaseBalls(){
        balls++;
    }

    /**
     * Overrides the observer's method update.
     * It receives any notificacion that happened to
     * an element of the game, i.e. a brick or a level
     * It receives a visitor to execute the desired logic.
     * @param o object that is being observed
     * @param arg message sent by the observed object
     */
    @Override
    public void update(Observable o, Object arg) {
        ((Visitor) arg).visitGame(this);
    }

    /**
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return finished;
    }

    public Level newLevelWithBricksFull(String s, int i, double i1, double i2, int i3) {
        Level newLevel = new PlayableLevel(s, i, i1, i2, i3);
        this.addObserverToBricks(newLevel);
        newLevel.subscribe(this);

        return newLevel;
    }

    /**
     * Subscribes every brick on the level to the game, which is
     * an observer
     * @param newLevel the level whose bricks are being observed.
     */
    private void addObserverToBricks(Level newLevel) {
        List<Brick> bricks = newLevel.getBricks();

        for (Brick brick: bricks){
            brick.subscribe(this);
        }
    }

    /**
     * Changes the current level to the following level
     */
    public void goNextLevel() {
        if (!currLevel.hasNextLevel()){
            finished = true;
        }
        currLevel = currLevel.getNextLevel();
    }

    /**
     * Obtains the list of bricks of the current level
     * @return A list of bricks
     */
    public List<Brick> getBricks() {
        return currLevel.getBricks();
    }

    /**
     * Obtains the number of balls available
     * @return number of balls
     */
    public int getBalls() {
        return balls;
    }

    /**
     * Increases the score of the game by the desired int extra
     * @param extra amount of score being added
     */
    public void increaseScore(int extra){
        currScore += extra;
    }

    /**
     * Obtains the current score of the game
     * @return current score
     */
    public int getCurrentPoints() {
        return currScore;
    }

    /**
     * Obtains the obtainable points in the current
     * level
     * @return obtainable points
     */
    public int getLevelPoints() {
        return currLevel.getPoints();
    }

    /**
     * Adds another level to the sequence of levels
     * of the game. The level is added at the end.
     * @param level level being added to the game
     */
    public void addPlayingLevel(Level level) {
        currLevel.addPlayingLevel(level);
    }

    /**
     * Changes the current level for the one in the parameter
     * @param level new current level
     */
    public void setCurrentLevel(Level level) {
        currLevel = level;
    }

    /**
     * Obtains the current level being played
     * @return current level
     */
    public Level getCurrentLevel() {
        return currLevel;
    }

    /**
     * Reduces by 1 the number of available balls, unless it is already 0.
     * @return remaining balls reduced by 1
     */
    public int dropBall() {
        if (balls > 0)
            balls--;

        return balls;
    }
}
