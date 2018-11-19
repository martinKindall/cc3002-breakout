package logic.brick;

import controller.GeneralEvent;
import logic.level.Level;

public interface BrickEvent extends GeneralEvent {
    void visitLevel(Level level);
}
