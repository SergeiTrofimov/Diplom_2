package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.diplom.Setup;
import ru.yandex.diplom.dbo.CreateUserRequest;
import ru.yandex.diplom.dbo.CreatedUser;
import ru.yandex.diplom.dbo.LoginUserRequest;
import ru.yandex.diplom.dbo.LoginUserResponse;

import static io.restassured.RestAssured.given;

public class UserClient {
    Setup setup = new Setup();

    @Step("Создать пользователя")
    public Response createUserRequest(CreatedUser user) {
        CreateUserRequest body = new CreateUserRequest(user.getEmail(), user.getPassword(), user.getName());
        Response response = given()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .body(body)
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
                .header("authorization", token)
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .delete(setup.getUserRud());
        return response;
    }

    @Step("Получить данные о пользователе")
    public Response getUserRequest(String token) {
        Response response = given()
                .header("authorization", "" + token)
                // .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .get(setup.getUserRud());
        return response;
    }

    @Step("Логинимся пользователем, чтобы получить токен и удаляем его.")
    public void clearTestData(CreatedUser user) {
        Response response = loginUserRequest(user.getEmail(), user.getPassword());
        String token = response.getBody().as(LoginUserResponse.class).getAccessToken();
        Response deleteResponse = deleteUserRequest(token);
    }
}