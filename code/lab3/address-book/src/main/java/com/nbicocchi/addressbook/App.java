package com.nbicocchi.addressbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nbicocchi.addressbook.controller.PersonOverviewController;
import com.nbicocchi.addressbook.persistence.DatabaseConfig;
import com.nbicocchi.addressbook.persistence.JdbcPersonRepository;

public class App extends Application {
    private final ExecutorService databaseExecutor = Executors.newSingleThreadExecutor(action -> {
        Thread thread = new Thread(action, "address-book-database");
        thread.setDaemon(true);
        return thread;
    });

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                App.class.getResource("person-overview-view.fxml")));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 960, 620));
        stage.setTitle("Address Book — Lab 3 (PostgreSQL)");
        stage.setMinWidth(820);
        stage.setMinHeight(560);
        stage.show();
        PersonOverviewController controller = loader.getController();
        controller.configure(new JdbcPersonRepository(DatabaseConfig.fromEnvironment()), databaseExecutor);
    }

    @Override
    public void stop() {
        databaseExecutor.shutdownNow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
