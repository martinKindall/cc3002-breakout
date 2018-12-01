package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

public class View extends GameApplication {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        Entity bg = GameFactory.newBackground();
        Entity player = GameFactory.newPlayer(300, 550);
        getGameWorld().addEntities(player, bg);
    }

    @Override
    protected void initInput() {
        Input input = getInput();
        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(ExampleType.PLAYER)
                        .forEach(e -> e.translateX(5));
            }
        }, KeyCode.D);
        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                getGameWorld().getEntitiesByType(ExampleType.PLAYER)
                        .forEach(e -> e.translateX(-5));
            }
        }, KeyCode.A);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
    }
}
