package logic.level;

import logic.brick.Brick;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractLevel implements Level {

    protected String name;
    protected Level next;


    public AbstractLevel(){
        name = "";
    }

    public AbstractLevel(String nombre){
        name = nombre;
        next = new InvalidLevel();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    @Override
    public Level getNextLevel() {
        return next;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel() {
        return next.isPlayableLevel();
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        next = next.addPlayingLevel(level);
        return this;
    }

    @Override
    public void setNextLevel(Level level){}

    @Override
    public List<Brick> getBricks() {
        return new LinkedList<>();
    }
}