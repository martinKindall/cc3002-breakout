package gui;

import facade.HomeworkTwoFacade;

public class ViewController {

    private GameState gameState;
    private HomeworkTwoFacade facade;

    public ViewController(){
        facade = GameFactory.newFacade();
        gameState = new GameNotReadyState();
    }
}
