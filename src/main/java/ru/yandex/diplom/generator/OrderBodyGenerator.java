package ru.yandex.diplom.generator;

import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.diplom.dbo.Ingredient;
import ru.yandex.diplom.dbo.IngredientResponse;
import ru.yandex.diplom.restclient.IngredientClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public List<String> generateRandomBurger() {
        List<String> burgerBody = new ArrayList<>();
        List<Ingredient> buns = ingredientTypeParser("bun");
        List<Ingredient> mains = ingredientTypeParser("main");
        List<Ingredient> sauces = ingredientTypeParser("sauce");
        Random r = new Random();
        burgerBody.add(buns.get(r.nextInt(buns.size() - 1)).get_id());
        for (int i = 0; i <= r.nextInt(4) + 1; i++) {
            burgerBody.add(mains.get(r.nextInt(mains.size() - 1)).get_id());
            burgerBody.add(sauces.get(r.nextInt(sauces.size() - 1)).get_id());
        }
        return burgerBody;
    }
}