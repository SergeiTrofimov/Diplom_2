package ru.yandex.diplom.dbo;

import java.util.ArrayList;
import java.util.List;

public class IngredientResponse {


    boolean success;
    List<Ingredient> data;

    //
    public IngredientResponse(boolean success, ArrayList<Ingredient> data) {
        this.success = success;
        this.data = data;
    }

    //
    public boolean isSuccess() {
        return success;
    }

    public List<Ingredient> getData() {
        return data;
    }
}