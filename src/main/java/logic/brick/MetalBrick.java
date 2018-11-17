package logic.brick;

import controller.Game;

public class MetalBrick extends AbstractBrick {

    public MetalBrick() {
        super(0, 10);
    }

    @Override
    public void accept(Game game){
        super.accept(game);

        game.increaseBalls();
    }
}
