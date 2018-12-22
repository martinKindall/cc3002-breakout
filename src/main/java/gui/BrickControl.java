package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;

public class BrickControl extends Component {

    private Brick brick;

    public BrickControl(Brick aBrick) {
        brick = aBrick;
    }

    public void hit(){
        brick.hit();
    }

    public boolean isDestroyed(){
        return brick.isDestroyed();
    }

    public Brick getBrick() {
        return brick;
    }
}
