package logic.visitor;

import controller.Game;
import logic.brick.Brick;
import logic.level.PlayableLevel;

public class AbstractDestroyedBrickVisitor implements Visitor{
    private Brick destroyedBrick;

    public AbstractDestroyedBrickVisitor(Brick brick){
        destroyedBrick = brick;
    }

    @Override
    public void visitGame(Game game){
        game.increaseScore(destroyedBrick.getScore());
    }

    @Override
    public void visitLevel(PlayableLevel level){
        level.increaseScore(destroyedBrick.getScore());
        level.decreaseBricks();
    }
}
