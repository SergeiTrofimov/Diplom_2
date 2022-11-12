import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.diplom.dbo.CreatedUser;
import ru.yandex.diplom.dbo.LoginUserResponse;
import ru.yandex.diplom.generator.OrderBodyGenerator;
import ru.yandex.diplom.generator.UserBodyGenerator;
import ru.yandex.diplom.restclient.OrderClient;
import ru.yandex.diplom.restclient.UserClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderTest {
    String token;
    UserClient userClient = new UserClient();
    OrderClient orderClient = new OrderClient();
    UserBodyGenerator userGenerator = new UserBodyGenerator();
    OrderBodyGenerator orderGenerator = new OrderBodyGenerator();
    CreatedUser user = userGenerator.generateRandomUser();
    Gson gson = new Gson();

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
    public void createOrderWithIngredientTest() {
        String burgerBody = orderGenerator.generateRandomBurger();
        Response orderResponse = orderClient.createOrderRequest(token, burgerBody);
        orderResponse.then().statusCode(200);
    }

    @Test
    public void createOrderWithoutIngredientTest() {
        List<String> ingredientList = new ArrayList<>();
        HashMap<String, List<String>> burgerMap = new HashMap<>();
        burgerMap.put("ingredients", ingredientList);
        String burgerBody = gson.toJson(burgerMap);
        Response orderResponse = orderClient.createOrderRequest(token, burgerBody);
        orderResponse.then().log().all().statusCode(400);
    }

    @Test
    public void createOrderWithWrongIngredientTest() {
        List<String> ingredientList = new ArrayList<>();
        ingredientList.add("66678aaa67633");
        HashMap<String, List<String>> burgerMap = new HashMap<>();
        burgerMap.put("ingredients", ingredientList);
        String burgerBody = gson.toJson(burgerMap);
        Response orderResponse = orderClient.createOrderRequest(token, burgerBody);
        orderResponse.then().log().all().header("Content-type", "text/html; charset=utf-8").statusCode(500);
    }

    @Test
    public void createOrderWithOutTokenTest() {
        String burgerBody = orderGenerator.generateRandomBurger();
        Response orderResponse = orderClient.createOrderRequest(" ", burgerBody);
        orderResponse.then().log().all().statusCode(200);
    }
}