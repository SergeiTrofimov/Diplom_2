package ru.yandex.diplom.dbo;

import java.util.ArrayList;

public class CreateOrderRequest {
    private ArrayList<Ingredient> ingredients;

    public CreateOrderRequest(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public CreateOrderRequest() {
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}