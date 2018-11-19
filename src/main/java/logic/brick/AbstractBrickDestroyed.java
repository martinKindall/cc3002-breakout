package logic.brick;

import controller.Game;
import logic.level.Level;

public abstract class AbstractBrickDestroyed implements BrickEvent {
    private Brick destroyedBrick;

    public AbstractBrickDestroyed(Brick brick){
        destroyedBrick = brick;
    }

    @Override
    public void visitGame(Game game){
        game.increaseScore(destroyedBrick.getScore());
    }

    @Override
    public void visitLevel(Level level){
        level.increaseScore(destroyedBrick.getScore());
    }
}
