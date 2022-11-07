package ru.yandex.diplom.generator;

import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.diplom.dbo.Ingredient;
import ru.yandex.diplom.dbo.IngredientResponse;
import ru.yandex.diplom.restclient.IngredientClient;

import java.util.ArrayList;
import java.util.List;

public class OrderBodyGenerator {
    public List<Ingredient> getAllIngredientToArray() {
        // получаем все ингридиенты
        IngredientClient ingredientClient = new IngredientClient();
        Response allIngredientResponse = ingredientClient.getIngredientRequest();
        List<Ingredient> ingredients = allIngredientResponse.getBody().as(IngredientResponse.class).getData();
        return ingredients;
    }

    public List<Ingredient> ingredientTypeParser(String ingredientType) {
        List<Ingredient> ingredients = getAllIngredientToArray();
        List<Ingredient> parsedIngredient = new ArrayList<>();
        for (Ingredient index : ingredients) {

            if (index.getType().equals(ingredientType)) {
                parsedIngredient.add(index);
            }
        }
        return parsedIngredient;
    }

    @Test
    public void generateRandomBurger() {
        List<String> burgerBody = new ArrayList<>();
        List<Ingredient> buns = ingredientTypeParser("bun");
        List<Ingredient> mains = ingredientTypeParser("main");
        List<Ingredient> sauces = ingredientTypeParser("sauce");
        // burgerBody.add(buns.get(rnd(buns.size())).get_id());
    }
}