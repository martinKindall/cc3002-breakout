package gui;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.settings.GameSettings;

public class View extends GameApplication {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        Entity player = GameFactory.newPlayer(300, 550);
        getGameWorld().addEntity(player);
    }

    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Basic Game App");
        gameSettings.setVersion("0.1");
    }
}
