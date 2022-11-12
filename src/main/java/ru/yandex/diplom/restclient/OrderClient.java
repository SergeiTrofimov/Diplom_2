package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient extends ClientsSetup {
    @Step("Создать заказ")
    public Response createOrderRequest(String token, String burgerBody) { // Создание заказа
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .baseUri(setup.getBaseUri())
                .body(burgerBody)
                .when()
                .post(setup.getClientOrders()
                );
        return response;
    }
}