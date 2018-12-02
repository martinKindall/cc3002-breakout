package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;


public class View extends GameApplication {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        Entity bg = GameFactory.newBackground();
        Entity player = GameFactory.newPlayer(100, 550);
        Point2D playerPos = player.getPosition();
        double width = player.getWidth();

        Entity ball = GameFactory.newBall(playerPos.getX() + width/2, playerPos.getY());
        Entity walls = GameFactory.newWalls();
        getGameWorld().addEntities(player, bg, ball, walls);
    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(ExampleType.PLAYER, ExampleType.BALL)
                        .stream()
                        .filter(e -> e.getComponent(PhysicsComponent.class).getVelocityY() == 0)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(e.getPosition().add(5, 0)));
            }
        }, KeyCode.D);
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(ExampleType.PLAYER, ExampleType.BALL)
                        .stream()
                        .filter(e -> e.getComponent(PhysicsComponent.class).getVelocityY() == 0)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(e.getPosition().add(-5, 0)));
            }
        }, KeyCode.A);
        input.addAction(new UserAction("Move Ball") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(ExampleType.BALL)
                        .stream()
                        .filter(e -> e.getComponent(PhysicsComponent.class).getVelocityY() == 0)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).setLinearVelocity(5 * 60, -5 * 60));
            }
        }, KeyCode.SPACE);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(ExampleType.BALL, ExampleType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall,
                                                   HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeFromWorld();
                        }
                    }
                });
    }
}
