package logic.level;

import logic.brick.Brick;

import java.util.List;

public class InvalidLevel extends AbstractLevel{

    public InvalidLevel(){
        super();
    }

    @Override
    public Level getNextLevel() {
        return this;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel() {
        return false;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        return level;
    }
}
