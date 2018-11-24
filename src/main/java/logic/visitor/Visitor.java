package logic.visitor;

import controller.Game;
import logic.level.PlayableLevel;

public interface Visitor {
    void visitGame(Game game);

    void visitLevel(PlayableLevel level);
}
