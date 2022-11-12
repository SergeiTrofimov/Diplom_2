import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import ru.yandex.diplom.dbo.CreatedUser;
import ru.yandex.diplom.dbo.LoginUserResponse;
import ru.yandex.diplom.dbo.User;

import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest extends TestsSetup {

    // Create user test
    @Test
    @DisplayName("Cоздание обычного пользователя")
    public void createUserTest() { // создаем обычного пользователя
        Response response = userClient.createUserRequest(user);
        response.then().log().all().statusCode(200);
        userClient.clearTestData(user);
    }

    @Test
    @DisplayName("Нельзя создать дубликат пользователя")
    public void cantCreateDuplicateUserTest() { // не можем создать дубликат
        userClient.createUserRequest(user);
        Response response = userClient.createUserRequest(user);
        response.then().log().all().statusCode(403);
        userClient.clearTestData(user);
    }

    @Test
    @DisplayName("Нельзя создать пользователя с пустым телом запроса")
    public void cantCreateEmptyUserTest() { // не можем создать пользователя с пустым телом
        CreatedUser user = new CreatedUser(null, null, null);
        Response response = userClient.createUserRequest(user);
        response.then().log().all().statusCode(403);
    }

    // Логин пользователя
    @Test
    @DisplayName("Логин под созданным пользователем успешен")
    public void loginUserTest() { // логин под существующим пользователем
        userClient.createUserRequest(user);
        Response response = userClient.loginUserRequest(user.getEmail(), user.getPassword());
        response.then().log().all().statusCode(200);
        response.then().assertThat().body(notNullValue());
        userClient.clearTestData(user);
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    public void loginWrongCredentialUserTest() { // логин под существующим пользователем с неверным паролем
        userClient.createUserRequest(user);
        Response response = userClient.loginUserRequest(user.getEmail(), user.getName());
        response.then().log().all().statusCode(401);
        userClient.clearTestData(user);
    }

    //Изменение пользователя
    @Test
    @DisplayName("Пользователь может поменять данные с токеном")
    public void patchUserWithAuthorizationTest() // пользователь может поменять данные с токеном
    {
        userClient.createUserRequest(user);
        CreatedUser tempUser = userGenerator.generateRandomUser();
        User updatedUser = new User(tempUser.getEmail(), tempUser.getName());
        Response loginResponse = userClient.loginUserRequest(user.getEmail(), user.getPassword());
        String token = loginResponse.getBody().as(LoginUserResponse.class).getAccessToken();
        Response updateResponse = userClient.patchUserRequest(updatedUser, token);
        updateResponse.then().log().all().statusCode(200);
        user.setEmail(tempUser.getEmail());
        userClient.clearTestData(user);
    }

    @Test
    @DisplayName("Пользователь не может поменять данные без токена")
    public void patchUserWithoutAuthorizationTest() // пользователь не может поменять данные без токен
    {
        userClient.createUserRequest(user);
        CreatedUser tempUser = userGenerator.generateRandomUser();
        User updatedUser = new User(tempUser.getEmail(), tempUser.getName());
        Response loginResponse = userClient.loginUserRequest(user.getEmail(), user.getPassword());
        Response updateResponse = userClient.patchUserRequest(updatedUser, null);
        updateResponse.then().log().all().statusCode(401);
        userClient.clearTestData(user);
    }
}