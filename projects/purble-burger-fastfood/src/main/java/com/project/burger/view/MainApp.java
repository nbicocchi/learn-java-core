package com.project.burger.view;

import com.project.burger.controller.GameClock;
import com.project.burger.controller.MainController;
import com.project.burger.audio.SoundEffects;
import com.project.burger.audio.SoundEffects.Effect;
import com.project.burger.engine.GameState;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Application lifecycle and session coordination; layout lives in GameView. */
public class MainApp extends Application {
    private MainController controller;
    private GameView view;
    private GameClock clock;
    private Stage stage;
    private boolean resultScheduled;
    private int session;
    private final SoundEffects sound = new SoundEffects();

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        controller = new MainController();
        view = new GameView(controller, this::startGame, this::refresh, sound);
        clock = new GameClock(() -> {
            controller.getGameEngine().tick();
            if (controller.getGameEngine().getState() == GameState.PLAYING
                    && controller.getGameEngine().getRemainingSeconds() == 10) {
                sound.play(Effect.TIME_LOW);
            }
            refresh();
        });
        stage.setTitle("Purble Burger Fast Food");
        stage.setScene(new Scene(view, GameView.WIDTH, GameView.HEIGHT));
        stage.setMinWidth(800);
        stage.setMinHeight(600);
        stage.show();
    }

    private void startGame() {
        session++;
        resultScheduled = false;
        controller.handleStartGame();
        view.showGame();
        view.render();
        clock.restart();
        sound.play(Effect.START);
    }

    private void refresh() {
        view.render();
        if (controller.getGameEngine().getState() == GameState.GAME_OVER) {
            clock.stop();
            if (!resultScheduled) {
                resultScheduled = true;
                int endedSession = session;
                // A modal nested event loop must not start inside an animation callback.
                Platform.runLater(() -> {
                    if (stage.isShowing() && session == endedSession
                            && controller.getGameEngine().getState() == GameState.GAME_OVER) {
                        sound.play(controller.getGameEngine().getScoreStars() == 5 ? Effect.WIN : Effect.GAME_OVER);
                        GameDialogView.show(stage, controller.getGameEngine().getScoreStars(), this::startGame);
                    }
                });
            }
        }
    }

    @Override
    public void stop() {
        if (clock != null) {
            clock.stop();
        }
        sound.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
