package gui;

import logic.level.Level;

public class GameNotReadyState extends GameState {

    @Override
    public void addNewLevel(Level level){
        super.facade.setCurrentLevel(level);
        super.view.setNextState(new GamePlayingState());
    }
}
