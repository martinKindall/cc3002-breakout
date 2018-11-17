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
    private Level curr;
    private int balls;

    public Game(int balls) {
        this.balls = balls;
        curr = new EntryLevel();
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
        curr.addPlayingLevel(new PlayableLevel(this, s, i, i1, i2, i3));
    }

    public void goNextLevel() {
        if (curr.hasNextLevel()){
            curr = curr.getNextLevel();
        }
    }

    public List<Brick> getBricks() {
        return curr.getBricks();
    }

    public int getBalls() {
        return balls;
    }
}
