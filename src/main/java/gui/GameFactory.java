package gui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class GameFactory {
    public static Entity newPlayer(double x, double y) {
        return Entities.builder()
                .at(x, y)
                .viewFromNode(new Rectangle(100, 30, Color.BLUE))
                .build();
    }
}
