package com.project.burger.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameDialogView {
    public static void show(Stage ownerStage, int finalScoreStars, Runnable onRestartCallback) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(ownerStage);
        dialogStage.initStyle(StageStyle.TRANSPARENT);

        boolean isVictory = finalScoreStars == 5;
        Label titleLabel = new Label(isVictory ? "WELL DONE!" : "GAME OVER");
        String titleColor = isVictory ? "#2E7D32" : "#D32F2F";
        titleLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: " + titleColor + ";");

        String chefImage = isVictory ? "/assets/chef.png" : "/assets/chef_sad.png";
        ImageView chefImageView = createImageView(chefImage, 400, 300);

        HBox imagesBox = new HBox(15);
        imagesBox.setAlignment(Pos.CENTER);
        imagesBox.getChildren().add(chefImageView);

        if (isVictory) {
            ImageView packageImageView = createImageView("/assets/package.png", 300, 200);
            packageImageView.setTranslateX(-90);
            packageImageView.setTranslateY(-20);
            imagesBox.getChildren().add(packageImageView);
        }

        HBox starsBox = new HBox(6);
        starsBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < 5; i++) {
            boolean isFilled = i < finalScoreStars;
            Label star = new Label(isFilled ? "★" : "☆");
            star.setStyle("-fx-font-size: 50px;");
            star.setTextFill(i < finalScoreStars ? Color.web("#F2B632") : Color.web("#D6B36A"));
            starsBox.getChildren().add(star);
        }

        Button restartButton = new Button(isVictory ? "Play Again" : "Try Again");
        String buttonColor = isVictory ? "#4CAF50" : "#D32F2F";
        restartButton.setPrefSize(180, 45);
        restartButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: " + buttonColor + "; " +
                "-fx-text-fill: white; -fx-cursor: hand; -fx-background-radius: 10;");

        restartButton.setOnAction(event -> {
            dialogStage.close();
            if (onRestartCallback != null) {
                onRestartCallback.run();
            }
        });

        VBox contentBox = new VBox(20, titleLabel, imagesBox, starsBox, restartButton);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(30));
        contentBox.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #D6B36A; " +
                "-fx-border-width: 3px; -fx-border-radius: 15; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 15, 0, 0, 0);");

        Scene scene = new Scene(contentBox);
        scene.setFill(Color.TRANSPARENT);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    private static ImageView createImageView(String resourcePath, double width, double height) {
        ImageView imageView = new ImageView(ImageCache.get(resourcePath));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}
