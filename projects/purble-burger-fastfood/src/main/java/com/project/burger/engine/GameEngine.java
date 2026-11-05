package com.project.burger.engine;

import com.project.burger.model.Burger;
import com.project.burger.model.Order;

public class GameEngine {
    private static final int MAX_BURGERS = 5;
    private static final int MAX_SCORE_STARS = 5;
    private static final int GAME_SECONDS = 60;
    private static final int CORRECT_ORDER_BONUS_SECONDS = 20;

    private GameState state;
    private Order currentOrder;
    private final OrderGenerator orderGenerator;
    private int currentBurgerCount;
    private int scoreStars;
    private int remainingSeconds;

    public GameEngine() {
        state = GameState.MENU;
        orderGenerator = new OrderGenerator();
    }

    public void startGame() {
        currentBurgerCount = 1;
        scoreStars = 0;
        remainingSeconds = GAME_SECONDS;
        state = GameState.PLAYING;
        currentOrder = orderGenerator.generateRandomOrder();
    }

    public boolean submitBurger(Burger burger) {
        if (state != GameState.PLAYING) {
            return false;
        }
        boolean correct = currentOrder != null && currentOrder.matches(burger);
        if (correct) {
            if (scoreStars < MAX_SCORE_STARS) { scoreStars++; }
        }

        if (currentBurgerCount >= MAX_BURGERS) {
            state = GameState.GAME_OVER;
        } else {
            if (correct) {
                remainingSeconds += CORRECT_ORDER_BONUS_SECONDS;
            }
            currentBurgerCount++;
            currentOrder = orderGenerator.generateRandomOrder();
        }
        return correct;
    }

    /** One elapsed game second; ignored before play and after the game has ended. */
    public void tick() {
        if (state != GameState.PLAYING) {
            return;
        }
        remainingSeconds--;
        if (remainingSeconds == 0) {
            state = GameState.GAME_OVER;
        }
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public GameState getState() {
        return state;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public int getCurrentBurgerCount() {
        return currentBurgerCount;
    }

    public int getScoreStars() {
        return scoreStars;
    }
}
