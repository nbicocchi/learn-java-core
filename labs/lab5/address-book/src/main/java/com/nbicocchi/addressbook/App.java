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
import com.nbicocchi.addressbook.client.RestPersonClient;

public class App extends Application {
    private RestPersonClient client;
    private final ExecutorService requestExecutor = Executors.newSingleThreadExecutor(action -> {
        Thread thread = new Thread(action, "address-book-http");
        thread.setDaemon(true);
        return thread;
    });

    @Override
    public void start(Stage stage) throws IOException {
        client = new RestPersonClient(System.getenv().getOrDefault("ADDRESSBOOK_API_URL", "http://localhost:8080"));
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(
                App.class.getResource("person-overview-view.fxml")));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 960, 620));
        stage.setTitle("Address Book — Lab 5 (REST client)");
        stage.setMinWidth(820);
        stage.setMinHeight(560);
        stage.show();
        PersonOverviewController controller = loader.getController();
        controller.configure(client, requestExecutor);
    }

    @Override
    public void stop() {
        requestExecutor.shutdownNow();
        if (client != null) {
            client.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
