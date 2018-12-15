package gui;

import facade.HomeworkTwoFacade;
import logic.level.Level;

public class GameState {

    protected HomeworkTwoFacade facade;
    protected View view;

    public void addNewLevel(Level level){}

    public void setFacade(HomeworkTwoFacade facade) {
        this.facade = facade;
    }

    public void setView(View view) {
        this.view = view;
    }
}
