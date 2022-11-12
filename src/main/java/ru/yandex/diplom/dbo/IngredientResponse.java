package ru.yandex.diplom.dbo;

import java.util.ArrayList;
import java.util.List;

public class IngredientResponse {
    //Перменные
    boolean success;
    List<Ingredient> data;

    //Конструктор
    public IngredientResponse(boolean success, ArrayList<Ingredient> data) {
        this.success = success;
        this.data = data;
    }

    //Геттеры
    public boolean isSuccess() {
        return success;
    }

    public List<Ingredient> getData() {
        return data;
    }
}