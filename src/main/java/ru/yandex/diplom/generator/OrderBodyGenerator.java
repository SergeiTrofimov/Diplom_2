package ru.yandex.diplom.generator;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.diplom.dbo.Ingredient;
import ru.yandex.diplom.dbo.IngredientResponse;
import ru.yandex.diplom.restclient.IngredientClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class OrderBodyGenerator {
    Gson gson = new Gson();

    @Step("Список всех ингридиентов")
    public List<Ingredient> getAllIngredientToArray() { // Делаем список всех ингридиентов
        // получаем все ингридиенты
        IngredientClient ingredientClient = new IngredientClient();
        Response allIngredientResponse = ingredientClient.getIngredientRequest();
        List<Ingredient> ingredients = allIngredientResponse.getBody().as(IngredientResponse.class).getData();
        return ingredients;
    }

    @Step("Разделяем ингридиенты по типам из общего списка")
    public List<Ingredient> ingredientTypeParser(String ingredientType) { // Разделяем ингридиенты по типам из общего списка
        List<Ingredient> ingredients = getAllIngredientToArray();
        List<Ingredient> parsedIngredient = new ArrayList<>();
        for (Ingredient index : ingredients) {

            if (index.getType().equals(ingredientType)) {
                parsedIngredient.add(index);
            }
        }
        return parsedIngredient;
    }

    @Step("Создаем случайны бургер")
    public String generateRandomBurger() { // Создаем случайный бургер
        List<String> ingredientList = new ArrayList<>();
        List<Ingredient> buns = ingredientTypeParser("bun");
        List<Ingredient> mains = ingredientTypeParser("main");
        List<Ingredient> sauces = ingredientTypeParser("sauce");
        Random r = new Random();
        ingredientList.add(buns.get(r.nextInt(buns.size() - 1)).get_id());
        for (int i = 0; i <= r.nextInt(4) + 1; i++) {
            ingredientList.add(mains.get(r.nextInt(mains.size() - 1)).get_id());
            ingredientList.add(sauces.get(r.nextInt(sauces.size() - 1)).get_id());
        }
        HashMap<String, List<String>> burgerMap = new HashMap<>();
        burgerMap.put("ingredients", ingredientList);
        String burgerBody = gson.toJson(burgerMap);
        return burgerBody;
    }
}