package logic.visitor;

import logic.brick.Brick;

public class CommonBrickDestroyedVisitor extends AbstractDestroyedBrickVisitor {

    public CommonBrickDestroyedVisitor(Brick brick){
        super(brick);
    }
}
