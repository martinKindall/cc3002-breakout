package logic.visitor;

import controller.Game;
import logic.level.PlayableLevel;

public class FinishedLevelVisitor implements Visitor {

    @Override
    public void visitGame(Game game){
        game.goNextLevel();
    }

    @Override
    public void visitLevel(PlayableLevel level){}
}
