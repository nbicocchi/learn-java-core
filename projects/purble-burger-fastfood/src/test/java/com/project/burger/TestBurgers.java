package com.project.burger;

import com.project.burger.model.Burger;
import com.project.burger.model.Ingredient;
import com.project.burger.model.Order;

public final class TestBurgers {
    private TestBurgers() {
    }

    public static Burger matching(Order order) {
        Burger burger = new Burger();
        burger.setShape(order.getTargetShape());
        order.getExpectedIngredients().forEach(type -> burger.addIngredient(new Ingredient(type, type.name(), "")));
        burger.setFriesPresent(order.isTargetFries());
        burger.addSauce(order.getTargetSauce());
        return burger;
    }
}
