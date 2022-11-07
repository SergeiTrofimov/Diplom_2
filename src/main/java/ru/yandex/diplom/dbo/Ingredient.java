package ru.yandex.diplom.dbo;

public class Ingredient {
    // Перменные
    private String id;
    private String ingredientType;


    // Конструктор
    public Ingredient(String id, String ingredientType) {
        this.id = id;
        this.ingredientType = ingredientType;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getIngredientType() {
        return ingredientType;
    }
}