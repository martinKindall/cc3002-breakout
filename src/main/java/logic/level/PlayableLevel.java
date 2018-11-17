package logic.level;

import logic.brick.Brick;

import java.util.List;

public class PlayableLevel extends AbstractLevel{

    protected List<Brick> bricks;

    public PlayableLevel(String nombre){
        super(nombre);
    }

    @Override
    public List<Brick> getBricks() {
        return bricks;
    }

    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    @Override
    public boolean hasNextLevel() {
        return next.isPlayableLevel();
    }

    @Override
    public void setNextLevel(Level level) {
        next = level;
    }
}
