package gui;

import com.almasb.fxgl.app.DSLKt;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.settings.GameSettings;
import facade.HomeworkTwoFacade;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;


public class View extends GameApplication {

    private HomeworkTwoFacade facade;
    private Entity player;
    private int delta;
    private int deltaRight;
    private int deltaLeft;

    public static void main(String... args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        facade = GameFactory.newFacade();

        Entity bg = GameFactory.newBackground();
        player = GameFactory.newPlayer(100, 550);
        Entity walls = GameFactory.newWalls();
        getGameWorld().addEntities(player, walls, bg);
        generateBall();

        delta = 5;
        deltaRight = deltaLeft = delta;
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
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(e.getPosition().add(deltaRight, 0)));
            }
        }, KeyCode.L);
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(ExampleType.PLAYER, ExampleType.BALL)
                        .stream()
                        .filter(e -> e.getComponent(PhysicsComponent.class).getVelocityY() == 0)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).reposition(e.getPosition().add(-deltaLeft, 0)));
            }
        }, KeyCode.J);
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
        gameSettings.setTitle("Breakout Game");
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
                            facade.dropBall();

                            if (facade.isGameOver()){
                                showGameOver();
                            }
                            else{
                                generateBall();
                            }
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(ExampleType.PLAYER, ExampleType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity player, Entity wall,
                                                   HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("RIGHT")) {
                            deltaRight = 0;
                        }

                        if (boxWall.getName().equals("LEFT")) {
                            deltaLeft = 0;
                        }
                    }

                    @Override
                    protected void onCollisionEnd(Entity player, Entity wall) {
                        deltaRight = delta;
                        deltaLeft = delta;
                    }
                });
    }

    private void generateBall() {
        Point2D playerPos = player.getPosition();
        double width = player.getWidth();
        Entity ball = GameFactory.newBall(playerPos.getX() + width/2, playerPos.getY());
        getGameWorld().addEntity(ball);
    }

    private void showGameOver(){
        Text gameOver = GameFactory.newText("Game over");
        getGameScene().addUINode(gameOver);
        DSLKt.centerText(gameOver);
        player.removeFromWorld();
    }
}
