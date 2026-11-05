package com.project.burger.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("ClassCanBeRecord")
public class Order {
    private final BreadShape targetShape;
    private final List<IngredientType> targetIngredients;
    private final boolean targetFries;
    private final IngredientType targetSauce;

    public Order(BreadShape targetShape, List<IngredientType> targetIngredients,
                 boolean targetFries, IngredientType targetSauce) {
        this.targetShape = Objects.requireNonNull(targetShape);
        this.targetIngredients = List.copyOf(targetIngredients);
        this.targetFries = targetFries;
        this.targetSauce = targetSauce;
    }

    public BreadShape getTargetShape() {
        return targetShape;
    }

    public List<IngredientType> getTargetIngredients() {
        return targetIngredients;
    }

    public boolean isTargetFries() {
        return targetFries;
    }

    public IngredientType getTargetSauce() { return targetSauce; }

    public boolean matches(Burger burger) {
        if (burger == null) return false;

        List<IngredientType> actualIngredients = burger.getIngredients().stream()
                .map(Ingredient::getType)
                .toList();
        List<IngredientType> expectedSauces = targetSauce == null ? List.of() : List.of(targetSauce);

        return targetShape == burger.getShape()
                && getExpectedIngredients().equals(actualIngredients)
                && targetFries == burger.isFriesPresent()
                && expectedSauces.equals(burger.getSauces());
    }

    /** Canonical bottom-to-top recipe, shared by validation and the order preview. */
    public List<IngredientType> getExpectedIngredients() {
        List<IngredientType> expectedIngredients = new ArrayList<>();

        List<IngredientType> displayVeggies = List.of(
                IngredientType.LETTUCE, IngredientType.ONION,
                IngredientType.TOMATO, IngredientType.PICKLES, IngredientType.CHEESE
        );
        for (IngredientType type : displayVeggies) {
            if (targetIngredients.contains(type)) {
                expectedIngredients.add(type);
            }
        }

        IngredientType expectedMeat = (targetShape == BreadShape.BAGUETTE)
                ? IngredientType.HAM
                : (targetIngredients.contains(IngredientType.HAM) ? IngredientType.HAM : IngredientType.PATTY);

        expectedIngredients.add(expectedMeat);

        return List.copyOf(expectedIngredients.reversed());
    }
}
