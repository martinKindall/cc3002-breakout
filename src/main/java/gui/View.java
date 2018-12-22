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
import com.almasb.fxgl.ui.InGamePanel;
import facade.HomeworkTwoFacade;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import logic.brick.Brick;
import logic.level.Level;

import java.util.ArrayList;
import java.util.List;


public class View extends GameApplication {

    static HomeworkTwoFacade facade;
    static View currentView;
    private Entity player;
    private Entity currBall;
    private Text currMsg;
    private InGamePanel myPanel;
    private int remainingBalls;
    private int playedLevels;
    private int remainingLevels;

    private static int delta;
    private static int deltaRight;
    private static int deltaLeft;
    private static int lastLevelPoints;
    private static int lastCurrentPoints;
    private static GameState gameState;
    private static List<Entity> currentEntityBricks;

    public static void main(String... args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        currentView = this;
        initialize();
    }

    static void initialize(){

        currentView.getGameScene().removeUINode(currentView.getCurrMsg());

        facade = GameFactory.newFacade();

        gameState = new GameNotReadyState();

        Entity bg = GameFactory.newBackground();
        currentView.setPlayer(GameFactory.newPlayer(100, 550));
        Entity walls = GameFactory.newWalls();
        currentView.getGameWorld().addEntities(currentView.getPlayer(), walls, bg);
        currentView.generateBall();
        currentView.setPanel();

        delta = 5;
        deltaRight = deltaLeft = delta;

        lastLevelPoints = 0;
        lastCurrentPoints = 0;
        currentEntityBricks = new ArrayList<>();
    }

    private void setPanel() {
        myPanel = new InGamePanel();
        Text score = GameFactory.newText("Total Score: 0");
        Text playedLevels = GameFactory.newText("Played levels: 0");
        Text remainingLevels = GameFactory.newText("Remaining levels: 0");
        Text remainingBalls = GameFactory.newText("Remaining balls: 0");

        List<Text> listOfTexts = new ArrayList<>();
        listOfTexts.add(score);
        listOfTexts.add(playedLevels);
        listOfTexts.add(remainingLevels);
        listOfTexts.add(remainingBalls);

        int origY = 50;

        for(Text aText : listOfTexts){
            aText.setTranslateX(15);
            aText.setTranslateY(origY);

            origY += 50;

            myPanel.getChildren().add(aText);
        }
        getGameScene().addUINode(myPanel);
    }

    private Node getCurrMsg() {
        return currMsg;
    }

    private Entity getPlayer() {
        return player;
    }

    private void setPlayer(Entity newPlayer) {
        player = newPlayer;
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

        input.addAction(new UserAction("Restart Game") {
            @Override
            protected void onActionBegin() {
                View.restartGame();
            }
        }, KeyCode.Q);

        input.addAction(new UserAction("Toggle Menu") {
            @Override
            protected void onActionBegin() {
                if (myPanel.isOpen()){
                    myPanel.close();
                }
                else{
                    myPanel.open();
                }
            }
        }, KeyCode.W);
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

                            if (pointsReached()){
                                if (facade.winner()){
                                    showWinner();
                                    return;
                                }

                                currBall.removeFromWorld();
                                generateBall();
                                renderBricks();
                                updateLastLevelPoints();
                            }
                        }
                    }
                });
    }

    private void generateBall() {
        Point2D playerPos = player.getPosition();
        double width = player.getWidth();
        Entity ball = GameFactory.newBall(playerPos.getX() + width/2, playerPos.getY());
        currBall = ball;
        getGameWorld().addEntity(ball);
    }

    private void showGameOver(){
        showFinalMsg("Game over. Press Q to try again.");
    }

    private void showWinner(){
        showFinalMsg("You won! Press Q to play again.");
    }

    private void showFinalMsg(String msg){
        Text gameWin = GameFactory.newText(msg);
        getGameScene().addUINode(gameWin);
        DSLKt.centerText(gameWin);
        cleanScreen();
        currMsg = gameWin;
        setNextState(new GameFinishedState());
    }

    private void cleanScreen(){
        currentView.getGameWorld().removeEntities(player, currBall);
        for(Entity enti: currentEntityBricks){
            enti.removeFromWorld();
        }
    }

    public static void setNextState(GameState newGameState) {
        gameState = newGameState;
    }

    public static void addNewLevel(){
        System.out.println("paso por aca");
        Level newLevel = facade.newLevelWithBricksFull("uno", 1, 1, 0, 1);
        gameState.addNewLevel(newLevel);
    }

    public static void setCurrentLevel(Level level) {
        facade.setCurrentLevel(level);
        lastLevelPoints = facade.getLevelPoints();
        lastCurrentPoints = facade.getCurrentPoints();

        renderBricks();
    }

    public static void addPlayingLevel(Level level) {
        facade.addPlayingLevel(level);
    }

    private static boolean gameReady() {
        return gameState.gameReady();
    }

    private static void restartGame() {
        gameState.restart();
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

            Paint color = setColor(aBrick);

            Entity entiBrick = GameFactory.newBrick(brickWidth, brickHeight, xInit, yInit, aBrick, color);
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

    private static Paint setColor(Brick aBrick) {
        if (aBrick.isWooden()){
            return Color.BROWN;
        }

        if (aBrick.isGlass()){
            return Color.LIGHTCORAL;
        }

        if (aBrick.isMetal()){
            return Color.GRAY;
        }

        return Color.WHITE;
    }

    private static boolean pointsReached() {
        return facade.getCurrentPoints() - getLastCurrentPoints() == getLastLevelPoints();
    }

    private static int getLastCurrentPoints() {
        return lastCurrentPoints;
    }

    private static int getLastLevelPoints() {
        return lastLevelPoints;
    }

    private static void updateLastLevelPoints() {
        lastLevelPoints = facade.getLevelPoints();
        lastCurrentPoints = facade.getCurrentPoints();
    }
}
