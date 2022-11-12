import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.yandex.diplom.dbo.LoginUserResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderTest extends TestsSetup {

    @Before
    @Step("Создаем пользователя для тестов, логинимся им, получаем токен")
    public void beforeLogin() {
        userClient.createUserRequest(user);
        Response loginResponse = userClient.loginUserRequest(user.getEmail(), user.getPassword());
        token = loginResponse.getBody().as(LoginUserResponse.class).getAccessToken();
    }

    @After
    @Step("Удаляем тестовые данные")
    public void clearTest() {
        userClient.clearTestData(user);
    }

    @Test
    @DisplayName("Можно создать заказ с ингридиентами")
    public void createOrderWithIngredientTest() { // Можно создать заказ с ингридиентами
        String burgerBody = orderGenerator.generateRandomBurger();
        Response orderResponse = orderClient.createOrderRequest(token, burgerBody);
        orderResponse.then().statusCode(200);
    }

    @Test
    @DisplayName("При попытке создать заказ без ингридиентов вернется ошибка 400")
    public void createOrderWithoutIngredientTest() { // При попытке создать заказ без ингридиентов вернется ошибка 400
        List<String> ingredientList = new ArrayList<>();
        HashMap<String, List<String>> burgerMap = new HashMap<>();
        burgerMap.put("ingredients", ingredientList);
        String burgerBody = gson.toJson(burgerMap);
        Response orderResponse = orderClient.createOrderRequest(token, burgerBody);
        orderResponse.then().log().all().statusCode(400);
    }

    @Test
    @DisplayName("При попытке создать заказ с ингридентом с неверным хэшем упадет ошибка")
    public void createOrderWithWrongIngredientTest() { //При попытке создать заказ с ингридентом с неверным хэшем упадет ошибка
        List<String> ingredientList = new ArrayList<>();
        ingredientList.add("66678aaa67633");
        HashMap<String, List<String>> burgerMap = new HashMap<>();
        burgerMap.put("ingredients", ingredientList);
        String burgerBody = gson.toJson(burgerMap);
        Response orderResponse = orderClient.createOrderRequest(token, burgerBody);
        orderResponse.then().log().all().header("Content-type", "text/html; charset=utf-8").statusCode(500);
    }

    @Test
    @DisplayName("Можно создать заказ не передав токен пользователя")
    public void createOrderWithOutTokenTest() { // Можно создать заказ не передав токен пользователя
        String burgerBody = orderGenerator.generateRandomBurger();
        Response orderResponse = orderClient.createOrderRequest(" ", burgerBody);
        orderResponse.then().log().all().statusCode(200);
    }
}