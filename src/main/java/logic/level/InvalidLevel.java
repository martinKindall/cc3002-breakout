package logic.level;


public class InvalidLevel extends AbstractLevel{

    public InvalidLevel(){
        super();
    }

    @Override
    public Level getNextLevel() {
        return this;
    }

    @Override
    public boolean hasNextLevel() {
        return false;
    }

    @Override
    public Level addPlayingLevel(Level level) {
        return level;
    }

    @Override
    public void increaseScore(int score) {

    }
}
