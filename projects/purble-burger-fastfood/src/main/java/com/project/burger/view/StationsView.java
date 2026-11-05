package com.project.burger.view;

import com.project.burger.audio.SoundEffects;
import com.project.burger.audio.SoundEffects.Effect;
import com.project.burger.controller.ConveyorController;
import com.project.burger.controller.StationController;
import com.project.burger.model.BreadShape;
import com.project.burger.model.IngredientType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.Objects;

public class StationsView extends HBox {

    private final Runnable refreshCallback;
    private final ConveyorController conveyorController;

    private final Runnable onRestart;
    private final SoundEffects sound;
    private Button deliverButton;

    public StationsView(StationController stationController, ConveyorController conveyorController,
                        Runnable refreshCallback, Runnable onRestart, SoundEffects sound) {
        this.refreshCallback = refreshCallback;
        this.conveyorController = conveyorController;
        this.onRestart = Objects.requireNonNull(onRestart);
        this.sound = Objects.requireNonNull(sound);

        setAlignment(Pos.CENTER);
        setSpacing(12);

        getChildren().add(createStation("Buns", createShapeButtons(stationController)));

        Button pattyButton = createImageButton("/assets/ingredient_patty.png");
        pattyButton.setOnAction(event -> {
            if (getCurrentStation() == 1) {
                stationController.addAddition(IngredientType.PATTY);
                sound.play(Effect.ADD);
                triggerRefresh();
            }
        });

        Button hamButton = createImageButton("/assets/ingredient_ham.png");
        hamButton.setOnAction(event -> {
            if (getCurrentStation() == 1) {
                stationController.addAddition(IngredientType.HAM);
                sound.play(Effect.ADD);
                triggerRefresh();
            }
        });

        VBox meatGroup = new VBox(5, pattyButton, hamButton);
        meatGroup.setAlignment(Pos.CENTER);

        getChildren().add(createStation("Meat", meatGroup));

        getChildren().add(createStation("Veggies", createVeggieButtons(stationController)));

        getChildren().add(createStation("Additions", createCheesePicklesButtons(stationController)));

        Button friesButton = createImageButton("/assets/fries.png");
        friesButton.setOnAction(event -> {
            if (getCurrentStation() == 4) {
                stationController.addFries();
                sound.play(Effect.ADD);
                triggerRefresh();
            }
        });

        Button ketchupButton = createImageButton("/assets/sauce_ketchup.png");
        ketchupButton.setOnAction(event -> {
            if (getCurrentStation() == 4) {
                stationController.applySauce(IngredientType.KETCHUP);
                sound.play(Effect.ADD);
                triggerRefresh();
            }
        });

        Button mustardButton = createImageButton("/assets/sauce_mustard.png");
        mustardButton.setOnAction(event -> {
            if (getCurrentStation() == 4) {
                stationController.applySauce(IngredientType.MUSTARD);
                sound.play(Effect.ADD);
                triggerRefresh();
            }
        });

        VBox saucesGroup = new VBox(5, ketchupButton, mustardButton);
        saucesGroup.setAlignment(Pos.CENTER);

        VBox friesAndSaucesGroup = new VBox(8, friesButton, saucesGroup);
        friesAndSaucesGroup.setAlignment(Pos.CENTER);
        getChildren().add(createStation("Fries & Dips", friesAndSaucesGroup));

        VBox actionButtonsGroup = getActionButtonsGroup(stationController);
        actionButtonsGroup.setStyle("-fx-background-color: rgba(255, 255, 255, 0.85); -fx-padding: 14 18; -fx-background-radius: 8;");
        getChildren().add(createStation("Actions", actionButtonsGroup));
    }

    public void setPlaying(boolean playing) {
        for (int i = 0; i < 5; i++) {
            getChildren().get(i).setDisable(!playing);
        }
        deliverButton.setDisable(!playing);
        // Restart stays available even after a result dialog is dismissed.
    }
    
    private VBox getActionButtonsGroup(StationController stationController) {

        deliverButton = new Button("Deliver");
        deliverButton.setStyle("-fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 8 16; -fx-background-radius: 6;");
        deliverButton.setOnAction(event -> {
            boolean correct = stationController.deliverBurger();
            sound.play(correct ? Effect.CORRECT : Effect.WRONG);
            triggerRefresh();
        });

        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-font-weight: bold; -fx-background-color: #FFC107; -fx-text-fill: white; -fx-cursor: hand; -fx-font-size: 14px; -fx-padding: 8 16; -fx-background-radius: 6;");
        restartButton.setOnAction(event -> {
            onRestart.run();
        });

        VBox actionButtonsGroup = new VBox(6, deliverButton, restartButton);
        actionButtonsGroup.setAlignment(Pos.CENTER);
        return actionButtonsGroup;
    }

    private int getCurrentStation() {
        return conveyorController != null ? conveyorController.getCurrentStationIndex() : 0;
    }

    private void triggerRefresh() {
        if (refreshCallback != null) {
            refreshCallback.run();
        }
    }

    private VBox createStation(String name, javafx.scene.Node... controls) {
        VBox station = new VBox(5);
        station.setAlignment(Pos.CENTER);
        station.setStyle("-fx-background-color: #D3D3D3; -fx-padding: 8; -fx-background-radius: 8;");
        Label label = new Label(name);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 11px;");
        station.getChildren().add(label);
        station.getChildren().addAll(controls);
        return station;
    }

    private javafx.scene.Node[] createShapeButtons(StationController controller) {
        Button[] buttons = new Button[BreadShape.values().length];
        for (int i = 0; i < buttons.length; i++) {
            BreadShape shape = BreadShape.values()[i];
            buttons[i] = createImageButton("/assets/bun_" + shape.name().toLowerCase(Locale.ROOT) + "_top.png");
            buttons[i].setOnAction(event -> {
                if (getCurrentStation() == 0) {
                    controller.applyShape(shape);
                    sound.play(Effect.ADD);
                    triggerRefresh();
                }
            });
        }
        return buttons;
    }

    private javafx.scene.Node[] createVeggieButtons(StationController controller) {
        return new javafx.scene.Node[]{
                createIngredientBtn(controller, IngredientType.LETTUCE, 2),
                createIngredientBtn(controller, IngredientType.ONION, 2),
                createIngredientBtn(controller, IngredientType.TOMATO, 2)
        };
    }

    private javafx.scene.Node[] createCheesePicklesButtons(StationController controller) {
        return new javafx.scene.Node[]{
                createIngredientBtn(controller, IngredientType.CHEESE, 3),
                createIngredientBtn(controller, IngredientType.PICKLES, 3)
        };
    }

    private Button createIngredientBtn(StationController controller, IngredientType type, int requiredStation) {
        Button button = createImageButton("/assets/ingredient_" + type.name().toLowerCase(Locale.ROOT) + ".png");
        button.setOnAction(event -> {
            if (getCurrentStation() == requiredStation) {
                controller.addAddition(type);
                sound.play(Effect.ADD);
                triggerRefresh();
            }
        });
        return button;
    }

    private Button createImageButton(String resourcePath) {
        Button button = new Button();
        ImageView imageView = new ImageView(ImageCache.get(resourcePath));
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);
        button.setGraphic(imageView);
        button.setStyle("-fx-cursor: hand; -fx-background-color: #F5F5F5; -fx-border-color: #B0B0B0; -fx-border-radius: 4;");
        return button;
    }
}
