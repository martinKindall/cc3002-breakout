package gui;

import logic.level.Level;

public class GamePlayingState extends GameState{

    @Override
    public void addNewLevel(Level level){
        View.addPlayingLevel(level);
    }
}
