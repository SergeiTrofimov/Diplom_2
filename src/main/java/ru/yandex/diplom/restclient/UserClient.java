package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.diplom.Setup;
import ru.yandex.diplom.dbo.CreateUserRequest;
import ru.yandex.diplom.dbo.LoginUserRequest;

import static io.restassured.RestAssured.given;

public class UserClient {
    Setup setup = new Setup();

    @Step("Создать пользователя")
    public Response createUserRequest(String email, String password, String name) {
        CreateUserRequest user = new CreateUserRequest(email, password, name);
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .body(user)
                .when()
                .post(setup.getRegisterUser());
        return response;
    }

    @Step("Логин пользователя")
    public Response loginUserRequest(String email, String password) {
        LoginUserRequest user = new LoginUserRequest(email, password);
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .body(user)
                .when()
                .post(setup.getUserLogin());
        return response;
    }

    @Step("Удаление пользователя")
    public Response deleteUserRequest(String token) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .delete(setup.getUserRud());
        return response;
    }
    @Step("Получить данные о пользователе")
    public Response getUserRequest(String token) {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .get(setup.getUserRud());
        return response;
    }
}