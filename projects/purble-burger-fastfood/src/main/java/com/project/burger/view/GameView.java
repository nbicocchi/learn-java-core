package com.project.burger.view;

import com.project.burger.controller.MainController;
import com.project.burger.audio.SoundEffects;
import com.project.burger.audio.SoundEffects.Effect;
import com.project.burger.engine.GameEngine;
import com.project.burger.engine.GameState;
import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/** Builds and renders the screen; it neither owns a timer nor changes game rules. */
public final class GameView extends StackPane {
    public static final double WIDTH = 1000;
    public static final double HEIGHT = 700;

    private final MainController controller;
    private final SoundEffects sound;
    private final BorderPane layout = new BorderPane();
    private final OrderDisplayView orderDisplay = new OrderDisplayView();
    private final BurgerView burgerView = new BurgerView();
    private final Label timerLabel = new Label();
    private final Button soundButton = new Button();
    private final StationsView stations;
    private final Button previous = new Button("◀");
    private final Button next = new Button("▶");
    private final Pane gamePane;

    public GameView(MainController controller, Runnable onStart, Runnable onRefresh) {
        this(controller, onStart, onRefresh, new SoundEffects());
    }

    public GameView(MainController controller, Runnable onStart, Runnable onRefresh, SoundEffects sound) {
        this.controller = controller;
        this.sound = sound;
        stations = new StationsView(controller.getStationController(), controller.getConveyorController(),
                onRefresh, onStart, sound);
        configureArrow(previous, () -> {
            int before = controller.getConveyorController().getCurrentStationIndex();
            controller.moveConveyorPrevious();
            if (before != controller.getConveyorController().getCurrentStationIndex()) sound.play(Effect.MOVE);
            onRefresh.run();
        });
        configureArrow(next, () -> {
            int before = controller.getConveyorController().getCurrentStationIndex();
            controller.moveConveyorNext();
            if (before != controller.getConveyorController().getCurrentStationIndex()) sound.play(Effect.MOVE);
            onRefresh.run();
        });
        gamePane = createGamePane();
        layout.setBackground(Background.EMPTY);
        layout.setCenter(createMenu(onStart));
        layout.setId("game-content");
        layout.setMinSize(WIDTH, HEIGHT);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setMaxSize(WIDTH, HEIGHT);
        layout.resize(WIDTH, HEIGHT);
        layout.setClip(new Rectangle(WIDTH, HEIGHT));

        // Keep one logical playing field; scale every image, control and hit target together.
        Scale scale = new Scale(1, 1, 0, 0);
        var factor = Bindings.min(widthProperty().divide(WIDTH), heightProperty().divide(HEIGHT));
        scale.xProperty().bind(factor);
        scale.yProperty().bind(factor);
        layout.getTransforms().add(scale);
        Group scaledContent = new Group(layout);
        scaledContent.setAutoSizeChildren(false);
        getChildren().add(scaledContent);
        setMinSize(0, 0);
        setPrefSize(WIDTH, HEIGHT);
        setAppBackground("/assets/background_kitchen.jpg");
        render();
    }

    public void showGame() {
        layout.setCenter(gamePane);
        setAppBackground("/assets/background_workbench.jpg");
    }

    public void render() {
        GameEngine engine = controller.getGameEngine();
        int seconds = engine.getRemainingSeconds();
        timerLabel.setText(String.format("Time left: %02d:%02d", seconds / 60, seconds % 60));
        soundButton.setText(sound.isEnabled() ? "Sound: on" : "Sound: off");
        burgerView.moveConveyorTo(controller.getConveyorController().getCurrentStationIndex());
        burgerView.renderBurger(controller.getCurrentBurger());
        orderDisplay.renderOrder(engine.getCurrentOrder(), engine.getCurrentBurgerCount(), engine.getScoreStars());
        boolean playing = engine.getState() == GameState.PLAYING;
        stations.setPlaying(playing);
        previous.setDisable(!playing);
        next.setDisable(!playing);
    }

    private VBox createMenu(Runnable onStart) {
        ImageView logo = new ImageView(ImageCache.get("/assets/logo.png"));
        logo.setFitWidth(500);
        logo.setPreserveRatio(true);
        Button play = new Button("Play");
        play.setFocusTraversable(false);
        play.setTranslateY(-80);
        play.setPrefSize(300, 90);
        play.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-cursor: hand;"
                + "-fx-text-fill: white; -fx-background-color: #00E600; -fx-background-radius: 50;"
                + "-fx-border-color: white; -fx-border-width: 2px; -fx-border-radius: 50;");
        play.setOnAction(event -> onStart.run());
        play.setOnMouseEntered(event -> animatePlayButton(play, 1.1));
        play.setOnMouseExited(event -> animatePlayButton(play, 1.0));
        VBox menu = new VBox(0, logo, play);
        menu.setAlignment(Pos.CENTER);
        return menu;
    }

    private void animatePlayButton(Button button, double scale) {
        ScaleTransition transition = new ScaleTransition(Duration.millis(150), button);
        transition.setToX(scale);
        transition.setToY(scale);
        transition.play();
    }

    private Pane createGamePane() {
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        pane.setMaxSize(WIDTH, HEIGHT);
        timerLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #D32F2F;"
                + "-fx-background-color: rgba(255,255,255,0.9); -fx-padding: 8 15; -fx-background-radius: 8;");
        timerLabel.setLayoutX(20);
        timerLabel.setLayoutY(20);
        soundButton.setText(sound.isEnabled() ? "Sound: on" : "Sound: off");
        soundButton.setLayoutX(840);
        soundButton.setLayoutY(20);
        soundButton.setPrefWidth(140);
        soundButton.setOnAction(event -> {
            sound.setEnabled(!sound.isEnabled());
            soundButton.setText(sound.isEnabled() ? "Sound: on" : "Sound: off");
        });
        orderDisplay.setMaxSize(200, 80);
        orderDisplay.setLayoutX(5);
        orderDisplay.setLayoutY(340);
        HBox controls = new HBox(10, previous, stations, next);
        controls.setAlignment(Pos.CENTER);
        // Keep the full station row inside the area to the right of the order card.
        // Its natural width depends on platform fonts, so fit it before scaling the game.
        Scale controlsScale = new Scale(1, 1, 0, 0);
        var controlsFactor = Bindings.createDoubleBinding(
                () -> Math.min(1, 730 / Math.max(1, controls.getLayoutBounds().getWidth())),
                controls.layoutBoundsProperty());
        controlsScale.xProperty().bind(controlsFactor);
        controlsScale.yProperty().bind(controlsFactor);
        controls.getTransforms().add(controlsScale);
        Group controlRow = new Group(controls);
        controlRow.setLayoutX(250);
        controlRow.setLayoutY(360);
        HBox dispensers = new HBox(0);
        dispensers.setAlignment(Pos.BOTTOM_CENTER);
        String[] paths = {"machine_shape", "machine_meat", "machine_veggies",
                "machine_pickles_cheese", "machine_fries_sauces"};
        double[] x = {0, -60, -110, -160, -240};
        double[] y = {-1, 0, 0, 3, 3};
        for (int i = 0; i < paths.length; i++) {
            ImageView machine = new ImageView(ImageCache.get("/assets/" + paths[i] + ".png"));
            machine.setFitHeight(240);
            machine.setPreserveRatio(true);
            machine.setTranslateX(x[i]);
            machine.setTranslateY(y[i]);
            dispensers.getChildren().add(machine);
        }
        dispensers.setLayoutX(167);
        dispensers.setLayoutY(30);
        burgerView.setLayoutX(100);
        burgerView.setLayoutY(252);
        pane.getChildren().addAll(timerLabel, soundButton, orderDisplay, dispensers, burgerView, controlRow);
        controlRow.toFront();
        return pane;
    }

    private void configureArrow(Button button, Runnable action) {
        button.setFocusTraversable(false);
        button.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 12 18; -fx-cursor: hand;");
        button.setOnAction(event -> action.run());
    }

    private void setAppBackground(String path) {
        BackgroundSize size = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,
                false, false, false, true);
        setBackground(new Background(new BackgroundImage(ImageCache.get(path), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, size)));
    }
}
