package logic.level;

import controller.Game;
import controller.GeneralEvent;

public class LevelFinished implements GeneralEvent {

    @Override
    public void visitGame(Game game){
        game.goNextLevel();
    }
}
