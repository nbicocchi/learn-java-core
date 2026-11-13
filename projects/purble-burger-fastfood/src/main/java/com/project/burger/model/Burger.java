package com.project.burger.model;

import java.util.ArrayList;
import java.util.List;

public class Burger {
    private BreadShape shape;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<IngredientType> sauces = new ArrayList<>();
    private boolean friesPresent;

    public Burger() {
        this.shape = null;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addSauce(IngredientType sauce) {
        if (sauce != null && !sauces.contains(sauce)) {
            sauces.add(sauce);
        }
    }

    public List<IngredientType> getSauces() { return List.copyOf(sauces); }

    public void setShape(BreadShape shape) {
        this.shape = shape;
    }

    public void setFriesPresent(boolean friesPresent) {
        this.friesPresent = friesPresent;
    }

    public void clear() {
        shape = null;
        ingredients.clear();
        friesPresent = false;
        sauces.clear();
    }

    public BreadShape getShape() {
        return shape;
    }

    public List<Ingredient> getIngredients() {
        return List.copyOf(ingredients);
    }

    public boolean isFriesPresent() {
        return friesPresent;
    }
}
