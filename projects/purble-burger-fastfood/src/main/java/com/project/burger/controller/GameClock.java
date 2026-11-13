package com.project.burger.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/** JavaFX scheduling only: the engine owns remaining time and the end-of-game rule. */
public final class GameClock {
    private final Timeline timeline;

    public GameClock(Runnable onSecond) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> onSecond.run()));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void restart() {
        timeline.playFromStart();
    }

    public void stop() {
        timeline.stop();
    }
}
