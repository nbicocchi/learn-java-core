package com.project.burger.model;

import com.project.burger.TestBurgers;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order() {
        return new Order(BreadShape.CLASSIC, List.of(IngredientType.PATTY), true, IngredientType.KETCHUP);
    }

    @Test
    void exactBurgerMatches() {
        Order order = order();
        assertTrue(order.matches(TestBurgers.matching(order)));
    }

    @Test
    void requestedSaucePlusAnExtraSauceDoesNotMatch() {
        Order order = order();
        Burger burger = TestBurgers.matching(order);
        burger.addSauce(IngredientType.MUSTARD);
        assertFalse(order.matches(burger));
    }

    @Test
    void missingOrWrongSauceDoesNotMatch() {
        Order order = order();
        Burger burger = new Burger();
        burger.setShape(BreadShape.CLASSIC);
        burger.addIngredient(new Ingredient(IngredientType.PATTY, "Patty", ""));
        burger.setFriesPresent(true);
        assertFalse(order.matches(burger));
        burger.addSauce(IngredientType.MUSTARD);
        assertFalse(order.matches(burger));
    }

    @Test
    void sauceIsRejectedWhenNoneWasRequested() {
        Order order = new Order(BreadShape.CLASSIC, List.of(IngredientType.PATTY), false, null);
        Burger burger = TestBurgers.matching(order);
        assertTrue(order.matches(burger));
        burger.addSauce(IngredientType.KETCHUP);
        assertFalse(order.matches(burger));
    }

    @Test
    void layerOrderBreadAndFriesArePartOfTheContract() {
        Order order = new Order(BreadShape.CLASSIC,
                List.of(IngredientType.PATTY, IngredientType.LETTUCE, IngredientType.CHEESE), true, null);
        assertEquals(List.of(IngredientType.PATTY, IngredientType.CHEESE, IngredientType.LETTUCE), order.getExpectedIngredients());
        Burger wrongOrder = new Burger();
        wrongOrder.setShape(BreadShape.CLASSIC);
        wrongOrder.setFriesPresent(true);
        wrongOrder.addIngredient(new Ingredient(IngredientType.LETTUCE, "Lettuce", ""));
        wrongOrder.addIngredient(new Ingredient(IngredientType.CHEESE, "Cheese", ""));
        wrongOrder.addIngredient(new Ingredient(IngredientType.PATTY, "Patty", ""));
        assertFalse(order.matches(wrongOrder));
        Burger burger = TestBurgers.matching(order);
        burger.setFriesPresent(false);
        assertFalse(order.matches(burger));
        burger.setFriesPresent(true);
        burger.setShape(BreadShape.BAGEL);
        assertFalse(order.matches(burger));
        assertFalse(order.matches(null));
    }

    @Test
    void baguetteRecipeUsesHamAndDuplicateIngredientsAreRejected() {
        Order order = new Order(BreadShape.BAGUETTE, List.of(IngredientType.CHEESE), false, null);
        assertEquals(List.of(IngredientType.HAM, IngredientType.CHEESE), order.getExpectedIngredients());
        Burger burger = TestBurgers.matching(order);
        assertTrue(order.matches(burger));
        burger.addIngredient(new Ingredient(IngredientType.CHEESE, "Cheese", ""));
        assertFalse(order.matches(burger));
    }

    @Test
    void orderListsCannotBeModifiedByTheCaller() {
        List<IngredientType> input = new ArrayList<>(List.of(IngredientType.PATTY));
        Order order = new Order(BreadShape.CLASSIC, input, false, null);
        input.clear();
        assertEquals(List.of(IngredientType.PATTY), order.getTargetIngredients());
        assertThrows(UnsupportedOperationException.class, () -> order.getTargetIngredients().clear());
        assertThrows(UnsupportedOperationException.class, () -> order.getExpectedIngredients().clear());
    }

    @Test
    void burgerListsAreReadOnlySnapshots() {
        Burger burger = TestBurgers.matching(order());
        List<Ingredient> ingredients = burger.getIngredients();
        List<IngredientType> sauces = burger.getSauces();
        assertThrows(UnsupportedOperationException.class, ingredients::clear);
        assertThrows(UnsupportedOperationException.class, sauces::clear);
        burger.clear();
        assertEquals(1, ingredients.size());
        assertEquals(List.of(IngredientType.KETCHUP), sauces);
        assertTrue(burger.getIngredients().isEmpty());
        assertTrue(burger.getSauces().isEmpty());
    }
}
