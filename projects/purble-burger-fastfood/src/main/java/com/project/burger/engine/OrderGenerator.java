package com.project.burger.engine;

import com.project.burger.model.BreadShape;
import com.project.burger.model.IngredientType;
import com.project.burger.model.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OrderGenerator {
    public Order generateRandomOrder() {
        ThreadLocalRandom random = ThreadLocalRandom.current();

        BreadShape shape = randomEnum(BreadShape.values(), random);

        List<IngredientType> ingredientPool = Arrays.stream(IngredientType.values())
                .filter(type -> !"SAUCE".equals(type.getCategory()) && !"SIDE".equals(type.getCategory()))
                .filter(type -> type != IngredientType.HAM)
                .toList();

        List<IngredientType> ingredients = new ArrayList<>(ingredientPool);
        Collections.shuffle(ingredients, random);
        ingredients = new ArrayList<>(ingredients.subList(0, random.nextInt(1, 5)));

        if (shape == BreadShape.BAGUETTE) {
            boolean hadPatty = false;
            for (int i = 0; i < ingredients.size(); i++) {
                if (ingredients.get(i) == IngredientType.PATTY) {
                    ingredients.set(i, IngredientType.HAM);
                    hadPatty = true;
                }
                if (!ingredients.contains(IngredientType.HAM) && !hadPatty) {
                    ingredients.remove(IngredientType.PATTY);
                }
            }

        } else if (shape == BreadShape.BAGEL) {
            if (random.nextBoolean()) {
                for (int i = 0; i < ingredients.size(); i++) {
                    if (ingredients.get(i) == IngredientType.PATTY) {
                        ingredients.set(i, IngredientType.HAM);
                    }
                }
            }
        }

        boolean fries = random.nextBoolean();
        IngredientType sauce = null;
        if (fries) {
            boolean wantsSauce = random.nextBoolean();

            if (wantsSauce) {
                int randomSauceIndex = random.nextInt(2);
                if (randomSauceIndex == 0) {
                    sauce = IngredientType.KETCHUP;
                } else {
                    sauce = IngredientType.MUSTARD;
                }
            }
        }

        return new Order(shape, ingredients, fries, sauce);
    }

    private <T> T randomEnum(T[] values, ThreadLocalRandom random) {
        return values[random.nextInt(values.length)];
    }
}
