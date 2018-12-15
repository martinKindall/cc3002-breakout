package gui;

import logic.level.Level;

public class GameNotReadyState extends GameState {

    @Override
    public void addNewLevel(Level level){
        View.setCurrentLevel(level);
        View.setNextState(new GamePlayingState());
    }
}
