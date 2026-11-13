package com.project.burger.view;

import com.project.burger.TestBurgers;
import com.project.burger.controller.GameClock;
import com.project.burger.controller.MainController;
import com.project.burger.engine.GameState;
import com.project.burger.model.BreadShape;
import com.project.burger.model.IngredientType;
import com.project.burger.model.Order;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.transform.Scale;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIfSystemProperty(named = "javafx.smoke", matches = "true")
class GameViewTest {
    @BeforeAll
    static void startToolkit() throws Exception {
        CompletableFuture<Void> ready = new CompletableFuture<>();
        Platform.startup(() -> {
            Platform.setImplicitExit(false);
            ready.complete(null);
        });
        ready.get(10, TimeUnit.SECONDS);
    }

    @AfterAll
    static void stopToolkit() {
        Platform.exit();
    }

    @Test
    void bundledImagesDecodeOnceAndAreReused() throws Exception {
        onFx(() -> {
            try (var files = Files.list(Path.of(GameViewTest.class.getResource("/assets").toURI()))) {
                List<Path> assets = files.toList();
                assertEquals(27, assets.size());
                for (Path file : assets) {
                    String name = "/assets/" + file.getFileName();
                    Image image = ImageCache.get(name);
                    assertFalse(image.isError(), name);
                    assertTrue(image.getWidth() > 0, name);
                    assertSame(image, ImageCache.get(name), name);
                }
            }
            assertTrue(ImageCache.find("/assets/order_display_frame.png").isEmpty());
            assertThrows(IllegalArgumentException.class, () -> ImageCache.get("/assets/missing.png"));
            return null;
        });
    }

    @Test
    void gameOverDisablesGameplayButRestartStillResetsTheWholeSession() throws Exception {
        onFx(() -> {
            MainController controller = new MainController();
            AtomicReference<GameView> reference = new AtomicReference<>();
            Runnable restart = () -> {
                controller.handleStartGame();
                reference.get().showGame();
                reference.get().render();
            };
            GameView view = new GameView(controller, restart, () -> reference.get().render());
            reference.set(view);
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(view, GameView.WIDTH, GameView.HEIGHT));
                stage.show();
                button(view, "Play").fire();
                assertTrue(view.lookupAll(".button").stream().map(Button.class::cast)
                        .noneMatch(button -> "Trash".equals(button.getText())));
                Order firstOrder = controller.getGameEngine().getCurrentOrder();
                var station = controller.getStationController();
                station.applyShape(firstOrder.getTargetShape());
                firstOrder.getExpectedIngredients().forEach(station::addAddition);
                if (firstOrder.isTargetFries()) station.addFries();
                station.applySauce(firstOrder.getTargetSauce());
                button(view, "Deliver").fire();
                assertEquals(80, controller.getGameEngine().getRemainingSeconds());
                assertTrue(view.lookupAll(".label").stream().map(Label.class::cast)
                        .anyMatch(label -> "Time left: 01:20".equals(label.getText())));
                button(view, "▶").fire();
                assertEquals(1, controller.getConveyorController().getCurrentStationIndex());
                while (controller.getGameEngine().getState() == GameState.PLAYING) controller.getGameEngine().tick();
                view.render();
                assertEquals(GameState.GAME_OVER, controller.getGameEngine().getState());
                for (String text : List.of("Deliver", "◀", "▶")) {
                    assertTrue(button(view, text).isDisabled(), text);
                }
                assertFalse(button(view, "Restart").isDisabled());
                button(view, "Restart").fire();
                assertEquals(GameState.PLAYING, controller.getGameEngine().getState());
                assertEquals(60, controller.getGameEngine().getRemainingSeconds());
                assertEquals(0, controller.getConveyorController().getCurrentStationIndex());
                assertFalse(button(view, "Deliver").isDisabled());
                for (int i = 0; i < 5; i++) {
                    button(view, "Deliver").fire();
                }
                assertEquals(GameState.GAME_OVER, controller.getGameEngine().getState());
                assertTrue(button(view, "Deliver").isDisabled());
                assertFalse(button(view, "Restart").isDisabled());
            } finally {
                stage.close();
            }
            return null;
        });
    }

    @Test
    void previewAndBurgerShareTheRecipeAndImagesEvenInATurkishLocale() throws Exception {
        onFx(() -> {
            Locale previous = Locale.getDefault();
            try {
                Locale.setDefault(Locale.forLanguageTag("tr-TR"));
                Order order = new Order(BreadShape.CLASSIC,
                        List.of(IngredientType.PATTY, IngredientType.CHEESE, IngredientType.ONION), false, null);
                OrderDisplayView preview = new OrderDisplayView();
                preview.renderOrder(order, 1, 0);
                List<IngredientType> displayed = imageViews(preview).stream()
                        .map(Node::getUserData).filter(IngredientType.class::isInstance)
                        .map(IngredientType.class::cast).toList();
                assertEquals(List.of(IngredientType.ONION, IngredientType.CHEESE, IngredientType.PATTY), displayed);
                BurgerView burger = new BurgerView();
                burger.renderBurger(TestBurgers.matching(order));
                List<Image> first = imageViews(burger).stream().map(ImageView::getImage).toList();
                burger.renderBurger(TestBurgers.matching(order));
                List<Image> second = imageViews(burger).stream().map(ImageView::getImage).toList();
                assertEquals(first.size(), second.size());
                for (int i = 0; i < first.size(); i++) assertSame(first.get(i), second.get(i));
            } finally {
                Locale.setDefault(previous);
            }
            return null;
        });
    }

    @Test
    void windowResizingScalesTheWholeGameUniformlyAndKeepsItCentered() throws Exception {
        onFx(() -> {
            MainController controller = new MainController();
            GameView view = new GameView(controller, () -> { }, () -> { });
            new Scene(view, GameView.WIDTH, GameView.HEIGHT);
            for (boolean playing : List.of(false, true)) {
                if (playing) {
                    controller.handleStartGame();
                    view.showGame();
                    view.render();
                }
                for (double[] size : List.of(new double[]{1000, 700}, new double[]{1600, 1120},
                        new double[]{1800, 900}, new double[]{800, 1000}, new double[]{800, 560})) {
                    view.resize(size[0], size[1]);
                    view.applyCss();
                    view.layout();
                    Node content = view.lookup("#game-content");
                    Scale scale = (Scale) content.getTransforms().getFirst();
                    double expected = Math.min(size[0] / GameView.WIDTH, size[1] / GameView.HEIGHT);
                    assertEquals(expected, scale.getX(), 0.001);
                    assertEquals(expected, scale.getY(), 0.001);
                    var bounds = content.localToScene(content.getLayoutBounds());
                    assertEquals(GameView.WIDTH * expected, bounds.getWidth(), 0.01);
                    assertEquals(GameView.HEIGHT * expected, bounds.getHeight(), 0.01);
                    // StackPane snaps positioning to physical pixels.
                    assertEquals((size[0] - bounds.getWidth()) / 2, bounds.getMinX(), 0.6);
                    assertEquals((size[1] - bounds.getHeight()) / 2, bounds.getMinY(), 0.6);
                    for (Node control : view.lookupAll(".button")) {
                        var controlBounds = control.localToScene(control.getLayoutBounds());
                        assertTrue(controlBounds.getMinX() >= -1 && controlBounds.getMaxX() <= size[0] + 1,
                                ((Button) control).getText() + " must remain inside the viewport");
                    }
                    assertTrue(view.getBackground().getImages().getFirst().getSize().isCover());
                }
            }
            return null;
        });
    }

    @Test
    void realApplicationShowsOneResultAndCanRestartAfterItIsDismissed() throws Exception {
        MainApp app = new MainApp();
        Stage stage = onFx(() -> {
            Stage window = new Stage();
            app.start(window);
            button(window.getScene().getRoot(), "Play").fire();
            for (int i = 0; i < 5; i++) button(window.getScene().getRoot(), "Deliver").fire();
            return window;
        });
        try {
            onFx(() -> {
                List<Window> results = Window.getWindows().stream()
                        .filter(window -> window != stage && window.isShowing()).toList();
                assertEquals(1, results.size());
                ((Stage) results.getFirst()).close();
                assertTrue(button(stage.getScene().getRoot(), "Deliver").isDisabled());
                return null;
            });
            onFx(() -> {
                button(stage.getScene().getRoot(), "Restart").fire();
                assertFalse(button(stage.getScene().getRoot(), "Deliver").isDisabled());
                assertEquals(1, Window.getWindows().stream().filter(Window::isShowing).count());
                return null;
            });
        } finally {
            onFx(() -> { app.stop(); stage.close(); return null; });
        }
    }

    @Test
    void resultDialogRequiresAllFiveStarsForVictory() throws Exception {
        Stage owner = onFx(() -> {
            Stage window = new Stage();
            window.setScene(new Scene(new javafx.scene.layout.StackPane(), 200, 100));
            window.show();
            return window;
        });
        try {
            for (int score = 0; score <= 5; score++) {
                int finalScore = score;
                Platform.runLater(() -> GameDialogView.show(owner, finalScore, () -> { }));
                onFx(() -> {
                    Stage result = (Stage) Window.getWindows().stream()
                            .filter(window -> window != owner && window.isShowing()).findFirst().orElseThrow();
                    try {
                        Parent root = result.getScene().getRoot();
                        String expected = finalScore == 5 ? "WELL DONE!" : "GAME OVER";
                        assertTrue(root.lookupAll(".label").stream().map(Label.class::cast)
                                .anyMatch(label -> expected.equals(label.getText())));
                        assertNotNull(button(root, finalScore == 5 ? "Play Again" : "Try Again"));
                    } finally {
                        result.close();
                    }
                    return null;
                });
            }
        } finally {
            onFx(() -> { owner.close(); return null; });
        }
    }

    @Test
    void clockCanStopAndRestartWithoutCreatingMultipleTickers() throws Exception {
        AtomicInteger ticks = new AtomicInteger();
        AtomicReference<CountDownLatch> signal = new AtomicReference<>(new CountDownLatch(1));
        GameClock clock = onFx(() -> new GameClock(() -> {
            ticks.incrementAndGet();
            signal.get().countDown();
        }));
        try {
            onFx(() -> { clock.restart(); return null; });
            assertTrue(signal.get().await(4, TimeUnit.SECONDS));
            CountDownLatch quiet = new CountDownLatch(1);
            int stoppedAt = onFx(() -> { clock.stop(); signal.set(quiet); return ticks.get(); });
            assertFalse(quiet.await(1200, TimeUnit.MILLISECONDS));
            assertEquals(stoppedAt, ticks.get());
            CountDownLatch resumed = new CountDownLatch(1);
            onFx(() -> { signal.set(resumed); clock.restart(); clock.restart(); return null; });
            assertTrue(resumed.await(4, TimeUnit.SECONDS));
            int restartedAt = onFx(() -> { clock.stop(); return ticks.get(); });
            assertEquals(stoppedAt + 1, restartedAt);
        } finally {
            onFx(() -> { clock.stop(); return null; });
        }
    }

    private static Button button(Parent root, String text) {
        return root.lookupAll(".button").stream().filter(Button.class::isInstance).map(Button.class::cast)
                .filter(button -> text.equals(button.getText())).findFirst().orElseThrow();
    }

    private static List<ImageView> imageViews(Node root) {
        List<ImageView> result = new ArrayList<>();
        if (root instanceof ImageView view) result.add(view);
        if (root instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) result.addAll(imageViews(child));
        }
        return result;
    }

    private static <T> T onFx(Callable<T> action) throws Exception {
        CompletableFuture<T> result = new CompletableFuture<>();
        Platform.runLater(() -> {
            try {
                result.complete(action.call());
            } catch (Throwable failure) {
                result.completeExceptionally(failure);
            }
        });
        return result.get(15, TimeUnit.SECONDS);
    }
}
