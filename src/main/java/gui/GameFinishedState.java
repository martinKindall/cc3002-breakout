package gui;

public class GameFinishedState extends GameState {

    @Override
    public void restart() {
        View.initialize();
        View.setNextState(new GameNotReadyState());
    }
}
