import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.diplom.dbo.CreateUserResponse;
import ru.yandex.diplom.dbo.CreatedUser;
import ru.yandex.diplom.dbo.LoginUserResponse;
import ru.yandex.diplom.generator.UserBodyGenerator;
import ru.yandex.diplom.restclient.UserClient;

import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest {
    UserClient userClient = new UserClient();
    UserBodyGenerator generator = new UserBodyGenerator();
    CreatedUser user = generator.generateRandomUser();



    @Test
    public void createUserTest() {
        Response response = userClient.createUserRequest(user);
        response.then().statusCode(200);
    }

    @Test
    public void loginUserTest() {
        userClient.createUserRequest(user);
        Response response = userClient.loginUserRequest(user.getEmail(), user.getPassword());
        response.then().statusCode(200);
        response.then().assertThat().body(notNullValue());
        userClient.clearTestData(user);
    }
}