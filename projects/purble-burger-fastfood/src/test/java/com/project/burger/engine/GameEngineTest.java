package com.project.burger.engine;

import com.project.burger.TestBurgers;
import com.project.burger.model.Burger;
import com.project.burger.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    @Test
    void submissionsAndTicksBeforeStartHaveNoEffect() {
        GameEngine engine = new GameEngine();
        engine.submitBurger(new Burger());
        engine.tick();
        assertEquals(GameState.MENU, engine.getState());
        assertNull(engine.getCurrentOrder());
        assertEquals(0, engine.getCurrentBurgerCount());
        assertEquals(0, engine.getScoreStars());
    }

    @Test
    void startInitializesTheWholeSession() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        assertEquals(GameState.PLAYING, engine.getState());
        assertEquals(1, engine.getCurrentBurgerCount());
        assertEquals(0, engine.getScoreStars());
        assertEquals(60, engine.getRemainingSeconds());
        assertNotNull(engine.getCurrentOrder());
    }

    @Test
    void fiveCorrectOrdersProduceFiveStarsAndFinishTheGame() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        for (int i = 1; i <= 5; i++) {
            engine.submitBurger(TestBurgers.matching(engine.getCurrentOrder()));
            assertEquals(i, engine.getScoreStars());
        }
        assertEquals(GameState.GAME_OVER, engine.getState());
        assertEquals(5, engine.getCurrentBurgerCount());
        assertEquals(140, engine.getRemainingSeconds()); // Four bonuses, none after the last order.
    }

    @Test
    void incorrectDeliveryAdvancesWithoutAwardingAStar() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        engine.submitBurger(new Burger());
        assertEquals(2, engine.getCurrentBurgerCount());
        assertEquals(0, engine.getScoreStars());
        assertEquals(60, engine.getRemainingSeconds());
    }

    @Test
    void sixthSubmissionCannotChangeTheFinalScore() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        for (int i = 0; i < 5; i++) {
            engine.submitBurger(new Burger());
        }
        Order lastOrder = engine.getCurrentOrder();
        engine.submitBurger(TestBurgers.matching(lastOrder));
        assertEquals(GameState.GAME_OVER, engine.getState());
        assertEquals(0, engine.getScoreStars());
        assertEquals(5, engine.getCurrentBurgerCount());
        assertSame(lastOrder, engine.getCurrentOrder());
    }

    @Test
    void expiryChangesEngineStateAndFreezesScoreAndTime() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        for (int i = 0; i < 59; i++) engine.tick();
        assertEquals(GameState.PLAYING, engine.getState());
        assertEquals(1, engine.getRemainingSeconds());
        engine.tick();
        assertEquals(GameState.GAME_OVER, engine.getState());
        assertEquals(0, engine.getRemainingSeconds());
        engine.submitBurger(TestBurgers.matching(engine.getCurrentOrder()));
        engine.tick();
        assertEquals(0, engine.getScoreStars());
        assertEquals(1, engine.getCurrentBurgerCount());
        assertEquals(0, engine.getRemainingSeconds());
    }

    @Test
    void restartResetsScoreOrdersAndTimeAfterAnExpiredGame() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        engine.submitBurger(TestBurgers.matching(engine.getCurrentOrder()));
        while (engine.getState() == GameState.PLAYING) engine.tick();
        engine.startGame();
        assertEquals(GameState.PLAYING, engine.getState());
        assertEquals(1, engine.getCurrentBurgerCount());
        assertEquals(0, engine.getScoreStars());
        assertEquals(60, engine.getRemainingSeconds());
    }

    @Test
    void correctDeliveryAddsTwentySecondsWithoutResettingTheCountdown() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        for (int i = 0; i < 12; i++) engine.tick();
        engine.submitBurger(TestBurgers.matching(engine.getCurrentOrder()));
        assertEquals(68, engine.getRemainingSeconds());
        assertEquals(1, engine.getScoreStars());
        assertEquals(2, engine.getCurrentBurgerCount());
        engine.tick();
        assertEquals(67, engine.getRemainingSeconds());
    }

    @Test
    void correctDeliveryWithOneSecondLeftKeepsTheNextOrderPlayable() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        for (int i = 0; i < 59; i++) engine.tick();
        engine.submitBurger(TestBurgers.matching(engine.getCurrentOrder()));
        assertEquals(GameState.PLAYING, engine.getState());
        assertEquals(21, engine.getRemainingSeconds());
        assertEquals(2, engine.getCurrentBurgerCount());
    }

    @Test
    void lastCorrectDeliveryEndsTheGameWithoutGrantingUnusedTime() {
        GameEngine engine = new GameEngine();
        engine.startGame();
        for (int i = 0; i < 4; i++) engine.submitBurger(TestBurgers.matching(engine.getCurrentOrder()));
        while (engine.getRemainingSeconds() > 1) engine.tick();
        engine.submitBurger(TestBurgers.matching(engine.getCurrentOrder()));
        assertEquals(GameState.GAME_OVER, engine.getState());
        assertEquals(5, engine.getScoreStars());
        assertEquals(1, engine.getRemainingSeconds());
    }
}
