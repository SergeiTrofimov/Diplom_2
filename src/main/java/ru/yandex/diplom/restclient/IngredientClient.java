package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.diplom.Setup;

import static io.restassured.RestAssured.given;

public class IngredientClient {
    Setup setup = new Setup();

    @Step("Получить все ингредиенты")
    public Response getIngredientRequest() {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .get(setup.getClientIngredient());
        return response;
    }
}