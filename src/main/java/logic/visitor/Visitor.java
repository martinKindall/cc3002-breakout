package logic.visitor;

/**
 * @author https://github.com/martinKindall
 *
 * Visitor allows us to define logic
 * that modifies Game or Level in an extensible way
 */

import controller.Game;
import logic.level.PlayableLevel;

public interface Visitor {

    /**
     * Visits game and modifies its state
     * @param game
     */
    void visitGame(Game game);

    /**
     * Visits a level and modifies its state
     * @param level
     */
    void visitLevel(PlayableLevel level);
}
