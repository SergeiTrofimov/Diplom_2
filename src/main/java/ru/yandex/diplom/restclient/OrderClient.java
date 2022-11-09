package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.diplom.Setup;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderClient {
    Setup setup = new Setup();

    @Step("Создать заказ")
    public Response createOrderRequest(String token, String burgerBody) {
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .header("authorization", token)
                .baseUri(setup.getBaseUri())
                .body(burgerBody)
                .when()
                .post(setup.getClientOrders()
                )
                ;
        return response;
    }
}