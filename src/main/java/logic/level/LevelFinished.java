package logic.level;

import controller.Game;

public class LevelFinished implements LevelEvent {

    @Override
    public void visitGame(Game game){
        game.goNextLevel();
    }
}
