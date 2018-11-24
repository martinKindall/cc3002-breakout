package logic.visitor;

/**
 * @author https://github.com/martinKindall
 *
 * Defines the behaviour of a finished level visitor
 */

import controller.Game;
import logic.level.PlayableLevel;

public class FinishedLevelVisitor implements Visitor {

    /**
     * When the level is finished, the visitor tells the game
     * to go to the next level
     * @param game current game
     */
    @Override
    public void visitGame(Game game){
        game.goNextLevel();
    }

    @Override
    public void visitLevel(PlayableLevel level){}
}
