package logic.brick;

public class Visitor {
    void visitMetalBrick(MetalBrick brick){
        brick.destroyedNotification();
    }
}
