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
import logic.brick.Brick;
import logic.level.Level;

import java.util.ArrayList;
import java.util.List;


public class View extends GameApplication {

    static HomeworkTwoFacade facade;
    static View currentView;
    private Entity player;
    private int delta;
    private int deltaRight;
    private int deltaLeft;
    private static int lastLevelPoints;
    private static GameState gameState;
    private static List<Entity> currentEntityBricks;

    public static void main(String... args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        facade = GameFactory.newFacade();
        currentView = this;

        gameState = new GameNotReadyState();

        Entity bg = GameFactory.newBackground();
        player = GameFactory.newPlayer(100, 550);
        Entity walls = GameFactory.newWalls();
        getGameWorld().addEntities(player, walls, bg);
        generateBall();

        delta = 5;
        deltaRight = deltaLeft = delta;

        lastLevelPoints = 0;
        currentEntityBricks = new ArrayList<>();
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
                        .filter(e -> View.gameReady())
                        .filter(e -> e.getComponent(PhysicsComponent.class).getVelocityY() == 0)
                        .forEach(e -> e.getComponent(PhysicsComponent.class).setLinearVelocity(5 * 60, -5 * 60));
            }
        }, KeyCode.SPACE);

        input.addAction(new UserAction("Add Level") {
            @Override
            protected void onActionBegin() {
                View.addNewLevel();
            }
        }, KeyCode.V);
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
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(ExampleType.BALL, ExampleType.BRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {
                        BrickControl ctrl = brick.getComponent(BrickControl.class);
                        ctrl.hit();

                        if (ctrl.isDestroyed()){
                            brick.removeFromWorld();

                            if (View.pointsReached()){
                                renderBricks();
                                View.updateLastLevelPoints();
                            }
                        }
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

    public static void setNextState(GameState newGameState) {
        gameState = newGameState;
    }

    public static void addNewLevel(){
        System.out.println("paso por aca");
        Level newLevel = facade.newLevelWithBricksNoMetal("uno", 1, 1, 0);
        gameState.addNewLevel(newLevel);
    }

    public static void setCurrentLevel(Level level) {
        facade.setCurrentLevel(level);

        renderBricks();
    }

    public static void addPlayingLevel(Level level) {
        facade.addPlayingLevel(level);
    }

    private static boolean gameReady() {
        return gameState.gameReady();
    }

    private static void renderBricks(){
        for(Entity enti: currentEntityBricks){
            enti.removeFromWorld();
        }

        currentEntityBricks = new ArrayList<>();

        List<Brick> currentBricks = facade.getBricks();

        double xInitOrig = 20, yInitOrig = 20;
        double xInit = xInitOrig, yInit = yInitOrig;
        int brickWidth = 70, brickHeight = 40;
        int bricksQty = currentBricks.size();

        for(Brick aBrick: currentBricks){
            Entity entiBrick = GameFactory.newBrick(brickWidth, brickHeight, xInit, yInit, aBrick);
            currentView.getGameWorld().addEntity(entiBrick);
            xInit += brickWidth;
            bricksQty--;

            if (bricksQty % 8 == 0){
                xInit = xInitOrig;
                yInit += brickHeight;
            }

            currentEntityBricks.add(entiBrick);
        }
    }

    private static boolean pointsReached() {
        return facade.getCurrentPoints() - getLastLevelPoints() == facade.getLevelPoints();
    }

    private static int getLastLevelPoints() {
        return lastLevelPoints;
    }

    private static void updateLastLevelPoints() {
        lastLevelPoints = facade.getCurrentPoints();
    }
}
