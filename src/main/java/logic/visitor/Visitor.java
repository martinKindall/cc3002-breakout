package logic.visitor;

import controller.Game;
import logic.level.Level;

public interface Visitor {
    void visitGame(Game game);

    void visitLevel(Level level);
}
