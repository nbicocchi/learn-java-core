package com.project.burger.controller;

import com.project.burger.engine.GameEngine;
import com.project.burger.engine.GameState;
import com.project.burger.model.BreadShape;
import com.project.burger.model.Burger;
import com.project.burger.model.Ingredient;
import com.project.burger.model.IngredientType;

public class StationController {
    private final Burger burger;
    private final GameEngine gameEngine;

    public StationController(Burger burger, GameEngine gameEngine) {
        this.burger = burger;
        this.gameEngine = gameEngine;
    }

    public void applyShape(BreadShape shape) {
        if (isPlaying()) {
            burger.setShape(shape);
        }
    }

    public void addAddition(IngredientType ingredientType) { addIngredient(ingredientType); }

    public void addFries() {
        if (isPlaying()) {
            burger.setFriesPresent(true);
        }
    }

    public void applySauce(IngredientType sauceType) {
        if (isPlaying()) {
            burger.addSauce(sauceType);
        }
    }

    public boolean deliverBurger() {
        if (isPlaying()) {
            boolean correct = gameEngine.submitBurger(burger);
            burger.clear();
            return correct;
        }
        return false;
    }

    private void addIngredient(IngredientType ingredientType) {
        if (isPlaying()) {
            burger.addIngredient(new Ingredient(ingredientType, ingredientType.name(), ""));
        }
    }

    private boolean isPlaying() {
        return gameEngine != null && gameEngine.getState() == GameState.PLAYING;
    }
}
