package com.nbicocchi.addressbook;

import com.nbicocchi.addressbook.controller.PersonEditDialogController;
import com.nbicocchi.addressbook.controller.PersonOverviewController;
import com.nbicocchi.addressbook.model.Person;
import com.nbicocchi.addressbook.persistence.PersonRepository;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIfSystemProperty(named = "javafx.smoke", matches = "true")
class ViewSmokeTest {
    private static Stage stage;

    @BeforeAll
    static void startJavaFx() throws Exception {
        CompletableFuture<Void> started = new CompletableFuture<>();
        Platform.startup(() -> {
            Platform.setImplicitExit(false);
            started.complete(null);
        });
        started.get(10, TimeUnit.SECONDS);
    }

    @AfterAll
    static void stopJavaFx() throws Exception {
        onFx(() -> {
            if (stage != null) {
                stage.close();
            }
            return null;
        });
        Platform.exit();
    }

    @Test
    @SuppressWarnings("unchecked")
    void databaseBackedScreenLoadsAndNewDialogSavesThroughRepository() throws Exception {
        Parent root = onFx(() -> {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("person-overview-view.fxml"));
            Parent view = loader.load();
            stage = new Stage();
            stage.setScene(new Scene(view, 960, 620));
            stage.show();
            PersonOverviewController controller = loader.getController();
            controller.configure(new FakeRepository(), Runnable::run);
            return view;
        });
        await(() -> !root.lookup("#contentPane").isDisabled());
        onFx(() -> {
            TableView<Person> table = (TableView<Person>) root.lookup("#personTable");
            assertEquals(1, table.getItems().size());
            table.getSelectionModel().selectFirst();
            assertEquals("00118", ((Label) root.lookup("#postalCodeLabel")).getText());
            ((Button) root.lookup("#clearSelectionButton")).fire();
            assertEquals("", ((Label) root.lookup("#firstNameLabel")).getText());
            // showAndWait runs a nested event loop, which executes this queued form fill.
            Platform.runLater(() -> {
                DialogPane pane = (DialogPane) Window.getWindows().stream()
                        .filter(window -> window != stage && window.isShowing())
                        .findFirst().orElseThrow().getScene().getRoot();
                ((TextField) pane.lookup("#firstNameField")).setText("New");
                ((TextField) pane.lookup("#lastNameField")).setText("Contact");
                ((TextField) pane.lookup("#streetField")).setText("Via Test 1");
                ((TextField) pane.lookup("#postalCodeField")).setText("00118");
                ((TextField) pane.lookup("#cityField")).setText("Roma");
                ((DatePicker) pane.lookup("#birthdayPicker")).setValue(LocalDate.of(2000, 1, 1));
                ((Button) pane.lookupButton(ButtonType.OK)).fire();
            });
            ((Button) root.lookup("#newButton")).fire();
            return null;
        });
        await(() -> !root.lookup("#contentPane").isDisabled());
        onFx(() -> {
            TableView<Person> table = (TableView<Person>) root.lookup("#personTable");
            assertEquals(2, table.getItems().size());
            assertEquals("New", table.getItems().getLast().getFirstName());
            return null;
        });
    }

    @Test
    void editFormCreatesNewValueAndRejectsMissingBirthday() throws Exception {
        onFx(() -> {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("person-edit-view.fxml"));
            DialogPane pane = loader.load();
            PersonEditDialogController editor = loader.getController();
            Person original = new Person(9L, "Anna", "Rossi", "Via Emilia 12", "41121", "Modena", LocalDate.of(1998, 3, 14));
            editor.setPerson(original);
            ((TextField) pane.lookup("#cityField")).setText("Roma");
            Person edited = editor.readPerson();
            assertEquals(9L, edited.getId());
            assertEquals("Roma", edited.getCity());
            assertEquals("Modena", original.getCity());
            ((DatePicker) pane.lookup("#birthdayPicker")).setValue(null);
            assertThrows(IllegalArgumentException.class, editor::readPerson);
            return null;
        });
    }

    private static <T> T onFx(Callable<T> action) throws Exception {
        CompletableFuture<T> result = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                result.complete(action.call());
            } catch (Throwable error) {
                result.completeExceptionally(error);
            }
        });
        return result.get(15, TimeUnit.SECONDS);
    }

    private static void await(Callable<Boolean> condition) throws Exception {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
        while (!onFx(condition)) {
            if (System.nanoTime() > deadline) {
                fail("Timed out waiting for JavaFX task completion");
            }
            Thread.sleep(20);
        }
    }

    private static final class FakeRepository implements PersonRepository {
        private final List<Person> people = new ArrayList<>(List.of(
                new Person(1L, "Luca", "Bianchi", "Via Roma 8", "00118", "Roma", LocalDate.of(1995, 7, 22))));

        public List<Person> findAll() {
            return new ArrayList<>(people);
        }

        public Optional<Person> findById(long id) {
            return people.stream().filter(person -> person.getId() == id).findFirst();
        }

        public Person save(Person person) {
            Person saved = new Person(2L, person.getFirstName(), person.getLastName(), person.getStreet(),
                    person.getPostalCode(), person.getCity(), person.getBirthday());
            people.add(saved);
            return saved;
        }

        public boolean deleteById(long id) {
            return people.removeIf(person -> person.getId() == id);
        }
    }
}
