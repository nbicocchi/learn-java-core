package com.project.burger.model;

@SuppressWarnings("ClassCanBeRecord")
public class Ingredient {
    private final IngredientType type;
    private final String name;
    private final String imagePath;

    public Ingredient(IngredientType type, String name, String imagePath) {
        this.type = type;
        this.name = name;
        this.imagePath = imagePath;
    }

    public IngredientType getType() {
        return type;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
