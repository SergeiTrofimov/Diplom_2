package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.diplom.dbo.CreateUserRequest;
import ru.yandex.diplom.Setup;

import static io.restassured.RestAssured.given;

public class UserClient {
    Setup setup = new Setup();

    @Step("Создать курьера")
    public Response createCourierRequest(String email, String password, String name) {
        CreateUserRequest user = new CreateUserRequest (email, password, name);
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .body(user)
                .when()
                .post(setup.getRegisterUser());
        return response;
    }
}
