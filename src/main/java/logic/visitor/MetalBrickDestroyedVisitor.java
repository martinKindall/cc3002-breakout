package logic.visitor;

import controller.Game;
import logic.brick.Brick;

public class MetalBrickDestroyedVisitor extends AbstractDestroyedBrickVisitor {

    public MetalBrickDestroyedVisitor(Brick brick){
        super(brick);
    }

    @Override
    public void visitGame(Game game){
        super.visitGame(game);
        game.increaseBalls();
    }
}
