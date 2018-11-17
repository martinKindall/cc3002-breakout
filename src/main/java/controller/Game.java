package controller;

import logic.brick.Brick;
import logic.level.EntryLevel;
import logic.level.Level;
import logic.level.PlayableLevel;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 */
public class Game implements Observer {
    private Level currLevel;
    private int balls;
    private int currScore;

    public Game(int balls) {
        this.balls = balls;
        currLevel = new EntryLevel();
        currScore = 0;
    }

    public void increaseBalls(){
        balls++;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Brick) {
            ((Brick) arg).accept(this);
        }
    }

    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return false;
    }

    public void newLevelWithBricksFull(String s, int i, int i1, int i2, int i3) {
        this.addPlayingLevel(new PlayableLevel(this, s, i, i1, i2, i3));
    }

    public void goNextLevel() {
        if (currLevel.hasNextLevel()){
            currLevel = currLevel.getNextLevel();
        }
    }

    public List<Brick> getBricks() {
        return currLevel.getBricks();
    }

    public int getBalls() {
        return balls;
    }

    public void increaseScore(int extra){
        currScore += extra;
    }

    public int getCurrentPoints() {
        return currScore;
    }

    public int getLevelPoints() {
        return currLevel.getPoints();
    }

    public void addPlayingLevel(Level level) {
        currLevel.addPlayingLevel(level);
    }

    public void setCurrentLevel(Level level) {
        currLevel = level;
    }

    public Level getCurrentLevel() {
        return currLevel;
    }
}
