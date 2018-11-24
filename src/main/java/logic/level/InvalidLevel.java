package logic.level;

/**
 * @author https://github.com/martinKindall
 *
 * Defines behaviour of an invalid level
 */

public class InvalidLevel extends AbstractLevel{

    /**
     * Call parent's constructor
     */
    public InvalidLevel(){
        super();
    }

    /**
     * The next level of an invalid level is itself,
     * it has reached the end of the level list.
     * @return pointer to same level
     */
    @Override
    public Level getNextLevel() {
        return this;
    }

    /**
     * By default, an invalid level has no next level
     * @return false always
     */
    @Override
    public boolean hasNextLevel() {
        return false;
    }

    /**
     * An invalid level cannot add a playing level,
     * that's why it returns the level instead, which has
     * it's own invalid level terminator.
     * @param level the level to be added
     * @return the level being added
     */
    @Override
    public Level addPlayingLevel(Level level) {
        return level;
    }
}
