package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class IngredientClient extends ClientsSetup {
    @Step("Получить все ингредиенты")
    public Response getIngredientRequest() { //Запрос получения всех ингридентов
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .get(setup.getClientIngredient());
        return response;
    }
}