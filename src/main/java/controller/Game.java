package controller;

import logic.brick.Brick;
import logic.level.InvalidLevel;
import logic.level.Level;
import logic.level.PlayableLevel;

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

    public Game(int balls) {
        this.balls = balls;
        currLevel = new InvalidLevel();
        currScore = 0;
        finished = false;
    }

    public void increaseBalls(){
        balls++;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof GeneralEvent) {
            ((GeneralEvent) arg).visitGame(this);
        }
        else{
            throw new RuntimeException("Observable no identificado");
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
        return finished;
    }

    public Level newLevelWithBricksFull(String s, int i, double i1, double i2, int i3) {
        Level newLevel = new PlayableLevel(s, i, i1, i2, i3);
        this.addObserverToBricks(newLevel);
        newLevel.subscribe(this);

        return newLevel;
    }

    private void addObserverToBricks(Level newLevel) {
        List<Brick> bricks = newLevel.getBricks();

        for (Brick brick: bricks){
            brick.subscribe(this);
        }
    }

    public void goNextLevel() {
        if (!currLevel.hasNextLevel()){
            finished = true;
        }
        currLevel = currLevel.getNextLevel();
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

    public int dropBall() {
        if (balls > 0)
            balls--;

        return balls;
    }
}
