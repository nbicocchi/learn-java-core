package com.nbicocchi.addressbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                App.class.getResource("person-overview-view.fxml")));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 960, 620));
        stage.setTitle("Address Book — Lab 1");
        stage.setMinWidth(820);
        stage.setMinHeight(560);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
