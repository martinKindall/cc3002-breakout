package logic.brick;

import controller.Game;

public class MetalBrickDestroyed extends AbstractBrickDestroyed {

    public MetalBrickDestroyed(Brick brick){
        super(brick);
    }

    @Override
    public void visitGame(Game game){
        super.visitGame(game);
        game.increaseBalls();
    }
}
