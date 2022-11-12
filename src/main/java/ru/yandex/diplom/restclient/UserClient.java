package ru.yandex.diplom.restclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.diplom.dbo.*;

import static io.restassured.RestAssured.given;

public class UserClient extends ClientsSetup {
    @Step("Создать пользователя")
    public Response createUserRequest(CreatedUser user) { // Запрос создания пользователя
        CreateUserRequest body = new CreateUserRequest(user.getEmail(), user.getPassword(), user.getName());
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .body(body)
                .when()
                .post(setup.getRegisterUser());
        return response;
    }

    @Step("Логин пользователя")
    public Response loginUserRequest(String email, String password) { // Запрос логинящий пользователя
        LoginUserRequest user = new LoginUserRequest(email, password);
        Response response = given().log().all()
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .body(user)
                .when()
                .post(setup.getUserLogin());
        return response;
    }

    @Step("Удаление пользователя")
    public Response deleteUserRequest(String token) { // Запрос удаления пользователя
        Response response = given().log().all()
                .header("authorization", token)
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .delete(setup.getUserRud());
        return response;
    }

    @Step("Получить данные о пользователе")
    public Response getUserRequest(String token) { // Запрос получения данных о пользователе
        Response response = given().log().all()
                .header("authorization", "" + token)
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .when()
                .get(setup.getUserRud());
        return response;
    }

    @Step("Изменить пользователя")
    public Response patchUserRequest(User user, String token) { // Запрос изменить пользователя
        Response response = given().log().all()
                .header("authorization", "" + token)
                .header("Content-type", "application/json")
                .baseUri(setup.getBaseUri())
                .body(user)
                .when()
                .patch(setup.getUserRud());
        return response;
    }

    @Step("Логинимся пользователем, чтобы получить токен и удаляем его.")
    public void clearTestData(CreatedUser user) { // Убираем за собой
        Response response = loginUserRequest(user.getEmail(), user.getPassword());
        String token = response.getBody().as(LoginUserResponse.class).getAccessToken();
        Response deleteResponse = deleteUserRequest(token);
    }
}