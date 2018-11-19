package logic.brick;

import controller.Game;

public class MetalBrick extends AbstractBrick {

    public MetalBrick() {
        super(0, 10);
    }

    @Override
    public void acceptGame(Game game){
        super.acceptGame(game);

        game.increaseBalls();
    }
}
