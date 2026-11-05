package com.nbicocchi.addressbook;

import com.nbicocchi.addressbook.model.Person;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIfSystemProperty(named = "javafx.smoke", matches = "true")
class ViewSmokeTest {
    @BeforeAll
    static void startJavaFx() throws Exception {
        CompletableFuture<Void> started = new CompletableFuture<>();
        Platform.startup(() -> started.complete(null));
        started.get(10, TimeUnit.SECONDS);
    }

    @AfterAll
    static void stopJavaFx() {
        Platform.exit();
    }

    @Test
    @SuppressWarnings("unchecked")
    void fxmlLoadsAndSelectionActionsUpdateDetails() throws Exception {
        CompletableFuture<Void> result = new CompletableFuture<>();
        Platform.runLater(() -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(App.class.getResource("person-overview-view.fxml"));
                stage.setScene(new Scene(root, 960, 620));
                stage.show();
                root.applyCss();
                root.layout();
                TableView<Person> table = (TableView<Person>) root.lookup("#personTable");
                Label firstName = (Label) root.lookup("#firstNameLabel");
                Label postalCode = (Label) root.lookup("#postalCodeLabel");
                Button clear = (Button) root.lookup("#clearSelectionButton");
                Button reload = (Button) root.lookup("#reloadSamplesButton");

                assertEquals(6, table.getItems().size());
                assertEquals("", firstName.getText());
                assertTrue(clear.isDisabled());
                table.getSelectionModel().select(1);
                assertEquals("Luca", firstName.getText());
                assertEquals("00118", postalCode.getText());
                assertEquals("Luca", table.getColumns().getFirst().getCellData(1));
                clear.fire();
                assertNull(table.getSelectionModel().getSelectedItem());
                assertEquals("", firstName.getText());
                assertTrue(clear.isDisabled());
                table.getItems().removeFirst();
                table.getSelectionModel().selectFirst();
                reload.fire();
                assertEquals(6, table.getItems().size());
                assertNull(table.getSelectionModel().getSelectedItem());
                assertEquals("", firstName.getText());
                result.complete(null);
            } catch (Throwable error) {
                result.completeExceptionally(error);
            } finally {
                stage.close();
            }
        });
        result.get(20, TimeUnit.SECONDS);
    }
}
