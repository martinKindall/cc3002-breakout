package gui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class GameFactory {


    public static Entity newPlayer(double x, double y) {
        return Entities.builder()
                .at(x, y)
                .type(ExampleType.PLAYER)
                .viewFromNode(new Rectangle(100, 30, Color.BLUE))
                .build();
    }

    public static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(600, 600, Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }
}
