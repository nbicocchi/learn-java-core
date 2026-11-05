package com.project.burger.model;

public enum IngredientType {
    PATTY("MEAT"),
    HAM("MEAT"),
    LETTUCE("ADDITION"),
    CHEESE("ADDITION"),
    ONION("ADDITION"),
    PICKLES("ADDITION"),
    TOMATO("ADDITION"),
    FRIES("SIDE"),
    KETCHUP("SAUCE"),
    MUSTARD("SAUCE");

    private final String category;

    IngredientType(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
