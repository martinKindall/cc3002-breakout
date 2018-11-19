package logic.brick;

import controller.Game;
import logic.level.Level;

public interface BrickEvent {

    void visitLevel(Level level);
    void visitGame(Game game);
}
