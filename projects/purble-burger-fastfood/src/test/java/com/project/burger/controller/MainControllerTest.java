package com.project.burger.controller;

import com.project.burger.engine.GameState;
import com.project.burger.model.BreadShape;
import com.project.burger.model.IngredientType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {
    @Test
    void restartResetsBurgerConveyorAndEngineTogether() {
        MainController main = new MainController();
        main.handleStartGame();
        main.moveConveyorNext();
        main.getStationController().applyShape(BreadShape.CLASSIC);
        main.getStationController().addAddition(IngredientType.PATTY);
        main.getStationController().addFries();
        main.getStationController().applySauce(IngredientType.KETCHUP);
        main.getGameEngine().tick();
        main.handleStartGame();
        assertNull(main.getCurrentBurger().getShape());
        assertTrue(main.getCurrentBurger().getIngredients().isEmpty());
        assertTrue(main.getCurrentBurger().getSauces().isEmpty());
        assertFalse(main.getCurrentBurger().isFriesPresent());
        assertEquals(0, main.getConveyorController().getCurrentStationIndex());
        assertEquals(60, main.getGameEngine().getRemainingSeconds());
        assertEquals(GameState.PLAYING, main.getGameEngine().getState());
    }

    @Test
    void actionsOutsidePlayingCannotAlterTheBurgerOrConveyor() {
        MainController main = new MainController();
        main.moveConveyorNext();
        main.getStationController().applyShape(BreadShape.CLASSIC);
        main.getStationController().deliverBurger();
        assertNull(main.getCurrentBurger().getShape());
        assertEquals(0, main.getConveyorController().getCurrentStationIndex());
        main.handleStartGame();
        main.moveConveyorNext();
        main.getStationController().applyShape(BreadShape.BAGEL);
        main.getStationController().addAddition(IngredientType.HAM);
        for (int i = 0; i < 60; i++) main.getGameEngine().tick();
        main.getStationController().applyShape(BreadShape.CLASSIC);
        main.getStationController().addAddition(IngredientType.CHEESE);
        main.getStationController().addFries();
        main.getStationController().applySauce(IngredientType.KETCHUP);
        main.getStationController().deliverBurger();
        main.moveConveyorPrevious();
        assertEquals(BreadShape.BAGEL, main.getCurrentBurger().getShape());
        assertEquals(1, main.getCurrentBurger().getIngredients().size());
        assertTrue(main.getCurrentBurger().getSauces().isEmpty());
        assertFalse(main.getCurrentBurger().isFriesPresent());
        assertEquals(1, main.getConveyorController().getCurrentStationIndex());
    }

    @Test
    void navigationDoesNotDeliverAndExplicitDeliveryClearsTheBurger() {
        MainController main = new MainController();
        main.handleStartGame();
        main.getStationController().applyShape(BreadShape.CLASSIC);
        for (int i = 0; i < 10; i++) main.moveConveyorNext();
        assertEquals(BreadShape.CLASSIC, main.getCurrentBurger().getShape());
        assertEquals(1, main.getGameEngine().getCurrentBurgerCount());
        assertEquals(4, main.getConveyorController().getCurrentStationIndex());
        main.getStationController().deliverBurger();
        assertEquals(2, main.getGameEngine().getCurrentBurgerCount());
        assertNull(main.getCurrentBurger().getShape());
    }
}
