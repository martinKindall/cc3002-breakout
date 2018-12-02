package gui;

import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import facade.HomeworkTwoFacade;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.level.Level;

public final class GameFactory {


    public static Entity newPlayer(double x, double y) {
        int playerWidth = 100, playerHeight = 30;

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.KINEMATIC);
        physics.setFixtureDef(
                new FixtureDef().restitution(1.2f).density(0.1f));

        return Entities.builder()
                .at(x, y)
                .type(ExampleType.PLAYER)
                .viewFromNodeWithBBox(new Rectangle(playerWidth, playerHeight, Color.BLUE))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    public static Entity newBall(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(
                new FixtureDef().restitution(1f).density(1f));
//        physics.setOnPhysicsInitialized(
//                () -> physics.setLinearVelocity(5 * 60, -5 * 60));
        return Entities.builder()
                .at(x, y)
                .type(ExampleType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.LIGHTCORAL))
                .with(physics, new CollidableComponent(true))
                .build();
    }

    public static Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(ExampleType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    public static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(600, 600, Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    public static Text newText(String message){
        Text text = new Text();
        text.setText(message);
        text.setFont(Font.font(18));

        return text;
    }

    public static HomeworkTwoFacade newFacade(){
        HomeworkTwoFacade facade = new HomeworkTwoFacade();
        Level first = facade.newLevelWithBricksNoMetal("uno", 10, 1, 0);
        facade.setCurrentLevel(first);

        return facade;
    }
}
