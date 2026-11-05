package com.project.burger.view;

import com.project.burger.model.BreadShape;
import com.project.burger.model.Burger;
import com.project.burger.model.Ingredient;
import com.project.burger.model.IngredientType;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BurgerView extends StackPane {
    private static final double STANDARD_WIDTH = 70;
    private static final double BAGUETTE_WIDTH = 120;
    private static final double INGREDIENT_HEIGHT = 70;
    private static final double BELT_WIDTH = 900;
    private static final double BELT_HEIGHT = 50;
    private static final double TRAY_WIDTH = 130;
    private static final double TRAY_HEIGHT = 30;
    private static final int STATION_COUNT = 5;
    private static final double[] DEFAULT_STATION_X = {70, 200, 400, 580, 750};

    private final Group beltLines = new Group();
    private final StackPane movingStack = new StackPane();
    private final StackPane burgerLayers = new StackPane();
    private final double[] stationX = DEFAULT_STATION_X.clone();
    private int currentStation;
    private TranslateTransition stackTransition;
    private TranslateTransition beltTransition;

    public BurgerView() {
        setPrefSize(BELT_WIDTH, 250);
        setMinSize(BELT_WIDTH, 250);
        setMaxSize(BELT_WIDTH, 250);

        this.setCache(true);
        this.setCacheHint(javafx.scene.CacheHint.SPEED);

        Pane belt = createConveyorBelt();
        StackPane.setAlignment(belt, Pos.BOTTOM_CENTER);

        movingStack.setPrefSize(BELT_WIDTH, 180);
        movingStack.setMinSize(BELT_WIDTH, 180);
        movingStack.setMaxSize(BELT_WIDTH, 180);
        movingStack.setAlignment(Pos.BOTTOM_LEFT);
        StackPane.setAlignment(movingStack, Pos.BOTTOM_LEFT);

        movingStack.setTranslateY(-135);

        Node tray = createTray();

        final double TRAY_VISUAL_WIDTH = TRAY_WIDTH + 40;

        burgerLayers.setAlignment(Pos.BOTTOM_CENTER);

        StackPane burgerContainer = new StackPane(burgerLayers);

        burgerContainer.setPrefWidth(STANDARD_WIDTH);
        burgerContainer.setMinWidth(STANDARD_WIDTH);
        burgerContainer.setMaxWidth(STANDARD_WIDTH);

        burgerContainer.setAlignment(Pos.BOTTOM_CENTER);

        final double BURGER_X_OFFSET = 0;
        final double BURGER_Y_OFFSET = 50;
        double centeredBurgerX = (TRAY_VISUAL_WIDTH - STANDARD_WIDTH) / 2;
        burgerContainer.setTranslateX(centeredBurgerX + BURGER_X_OFFSET);
        burgerContainer.setTranslateY(BURGER_Y_OFFSET);

        StackPane.setMargin(burgerContainer, new javafx.geometry.Insets(0, 0, BURGER_Y_OFFSET, 0));

        StackPane trayAndBurger = new StackPane();

        trayAndBurger.setPrefSize(TRAY_VISUAL_WIDTH, 180);
        trayAndBurger.setMinSize(TRAY_VISUAL_WIDTH, 180);
        trayAndBurger.setMaxSize(TRAY_VISUAL_WIDTH, 180);

        StackPane.setAlignment(tray, Pos.BOTTOM_LEFT);
        StackPane.setAlignment(burgerContainer, Pos.BOTTOM_LEFT);

        trayAndBurger.getChildren().addAll(tray, burgerContainer);

        StackPane.setAlignment(trayAndBurger, Pos.BOTTOM_LEFT);

        movingStack.getChildren().add(trayAndBurger);

        getChildren().addAll(belt, movingStack);
        moveStackTo(stationX[0], false);
    }

    private Pane createConveyorBelt() {
        Pane belt = new Pane();
        belt.setPrefSize(BELT_WIDTH, BELT_HEIGHT + 10); // +10 per lo spessore 3D

        double slantOffsetLeft = 70.0;
        double slantOffsetRight = 0.0;

        Polygon surface = new Polygon();
        surface.getPoints().addAll(
                slantOffsetLeft, 0.0,
                BELT_WIDTH - slantOffsetRight, 0.0,
                BELT_WIDTH, BELT_HEIGHT,
                0.0, BELT_HEIGHT
        );

        surface.setFill(Color.web("#5A5A5A"));
        surface.setStroke(Color.web("#333333"));
        surface.setStrokeWidth(2);
        surface.setStrokeType(StrokeType.INSIDE);

        Rectangle frontEdge = new Rectangle(BELT_WIDTH, 10);
        frontEdge.setTranslateY(BELT_HEIGHT);
        frontEdge.setFill(Color.web("#2B2B2B"));

        Polygon clipPolygon = new Polygon();
        clipPolygon.getPoints().addAll(
                slantOffsetLeft, 0.0,
                BELT_WIDTH - slantOffsetRight, 0.0,
                BELT_WIDTH, BELT_HEIGHT,
                0.0, BELT_HEIGHT
        );
        beltLines.setClip(clipPolygon);

        for (double x = -1000; x < BELT_WIDTH + 1000; x += 200) {
            double topX = x + slantOffsetLeft;
            Line line = new Line(topX, 0, x, BELT_HEIGHT);
            line.setStroke(Color.web("#444444"));
            line.setStrokeWidth(2);
            beltLines.getChildren().add(line);
        }

        belt.getChildren().addAll(surface, frontEdge, beltLines);
        return belt;
    }

    private Node createTray() {
        Polygon trayPolygon = new Polygon();

        double traySlant = 40.0;

        trayPolygon.getPoints().addAll(
                traySlant, 0.0,
                TRAY_WIDTH + traySlant, 0.0,
                TRAY_WIDTH, TRAY_HEIGHT,
                0.0, TRAY_HEIGHT
        );

        trayPolygon.setFill(Color.web("#777777"));
        trayPolygon.setStroke(Color.web("#333333"));
        trayPolygon.setStrokeWidth(2);

        return new StackPane(trayPolygon);
    }

    public void animateConveyor(boolean forward) {
        if (beltTransition != null) {
            beltTransition.stop();
        }

        beltTransition = new TranslateTransition(Duration.millis(450), beltLines);
        beltTransition.setByX(forward ? 200 : -200);
        beltTransition.setCycleCount(1);
        beltTransition.setInterpolator(Interpolator.EASE_BOTH);
        beltTransition.setOnFinished(e -> beltLines.setTranslateX(0));
        beltTransition.play();
    }

    public void moveConveyorTo(int stationIndex) {
        if (stationIndex < 0 || stationIndex >= STATION_COUNT) {
            throw new IllegalArgumentException("Station index must be between 0 and 4");
        }
        moveConveyorTo(stationIndex, stationIndex > currentStation);
    }

    private void moveConveyorTo(int stationIndex, boolean forward) {
        if (stationIndex == currentStation) {
            return;
        }
        currentStation = stationIndex;
        animateConveyor(forward);
        moveStackTo(stationX[currentStation], true);
    }

    private void moveStackTo(double targetX, boolean animated) {
        if (stackTransition != null) {
            stackTransition.stop();
        }
        final double TRAY_X_OFFSET = 40;
        double targetTranslateX = targetX - (TRAY_WIDTH / 2) + TRAY_X_OFFSET;
        if (!animated) {
            movingStack.setTranslateX(targetTranslateX);
            return;
        }
        stackTransition = new TranslateTransition(Duration.millis(450), movingStack);
        stackTransition.setToX(targetTranslateX);
        stackTransition.setInterpolator(Interpolator.EASE_BOTH);
        stackTransition.play();
    }

    public void renderBurger(Burger burger) {
        burgerLayers.getChildren().clear();
        if (burger == null || burger.getShape() == null) {
            return;
        }

        BreadShape shape = burger.getShape();
        boolean isBaguette = (shape == BreadShape.BAGUETTE);
        double bunWidth = isBaguette ? BAGUETTE_WIDTH : STANDARD_WIDTH;

        List<Node> layers = new ArrayList<>();

        ImageView topBun = createImageView(
                "/assets/bun_" + shape.name().toLowerCase(Locale.ROOT) + "_top.png", bunWidth, INGREDIENT_HEIGHT);
        topBun.setUserData(null);
        layers.add(topBun);

        List<Ingredient> ingredients = burger.getIngredients();
        for (int i = ingredients.size() - 1; i >= 0; i--) {
            IngredientType type = ingredients.get(i).getType();
            Node ingredientNode = createIngredientNode(ingredientImagePath(type), isBaguette);
            ingredientNode.setUserData(type);
            layers.add(ingredientNode);
        }

        ImageView bottomBun = createImageView(
                "/assets/bun_" + shape.name().toLowerCase(Locale.ROOT) + "_bottom.png", bunWidth, INGREDIENT_HEIGHT);
        bottomBun.setUserData(null);
        layers.add(bottomBun);

        layoutLayers(layers, isBaguette);

        // -------------------------------------------------
        // OUTSIDE OF STACKPANE
        // -------------------------------------------------

        if (burger.isFriesPresent()) {
            ImageView friesView = createImageView("/assets/fries.png", STANDARD_WIDTH, 70);
            friesView.setUserData(null);
            friesView.setTranslateX(50);
            friesView.setTranslateY(-87);
            friesView.setViewOrder(100); // fries behind the sandwich
            burgerLayers.getChildren().add(friesView);
        }

        for (IngredientType sauceType : burger.getSauces()) {
            ImageView sauceNode = createImageView(ingredientImagePath(sauceType), 40, 40);
            sauceNode.setUserData(sauceType);

            if (sauceType == IngredientType.KETCHUP) {
                sauceNode.setTranslateX(-60);
                sauceNode.setTranslateY(-70);
            } else if (sauceType == IngredientType.MUSTARD) {
                sauceNode.setTranslateX(-30);
                sauceNode.setTranslateY(-70);
            }

            burgerLayers.getChildren().add(sauceNode);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void layoutLayers(List<Node> layers, boolean isBaguette) {
        int totalElements = layers.size();
        if (totalElements == 0) return;

        double accumHeight = 0;
        for (int i = 0; i < totalElements - 1; i++) {
            accumHeight += getLayerStep(layers.get(i), isBaguette);
        }

        double currentY = -accumHeight - 55;

        for (int i = 0; i < totalElements; i++) {
            Node node = layers.get(i);
            node.setTranslateY(currentY);

            node.setViewOrder(i);
            burgerLayers.getChildren().add(node);

            currentY += getLayerStep(node, isBaguette);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private double getLayerStep(Node node, boolean isBaguette) {
        Object data = node.getUserData();

        if (!(data instanceof IngredientType ingredient)) {
            return isBaguette ? 10 : 13;
        }

        return switch (ingredient) {
            case LETTUCE, HAM -> 7;
            case CHEESE -> 3;
            case ONION -> 1.5;
            case PICKLES, TOMATO -> 8;
            case PATTY -> 9;
            default -> 11;
        };
    }

    private Node createIngredientNode(String resourcePath, boolean isBaguette) {
        return createImageView(resourcePath, isBaguette ? BAGUETTE_WIDTH : STANDARD_WIDTH, INGREDIENT_HEIGHT);
    }

    private String ingredientImagePath(IngredientType ingredient) {
        if (ingredient == null) {
            return "";
        }

        return switch (ingredient) {
            case KETCHUP -> "/assets/sauce_ketchup.png";
            case MUSTARD -> "/assets/sauce_mustard.png";
            case HAM -> "/assets/ingredient_ham.png";
            default -> "/assets/ingredient_" + ingredient.name().toLowerCase(Locale.ROOT) + ".png";
        };
    }

    private ImageView createImageView(String resourcePath, double fitWidth, double fitHeight) {
        ImageView imageView = new ImageView(ImageCache.get(resourcePath));
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        imageView.setPreserveRatio(false);
        return imageView;
    }

}
