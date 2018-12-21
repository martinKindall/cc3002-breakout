package gui;

import logic.level.Level;

public class GamePlayingState extends GameState{

    @Override
    public void addNewLevel(Level level){
        View.addPlayingLevel(level);
    }

    @Override
    public boolean gameReady(){
        return true;
    }

    @Override
    public void restart() {
        View.initialize();
        View.setNextState(new GameNotReadyState());
    }
}
