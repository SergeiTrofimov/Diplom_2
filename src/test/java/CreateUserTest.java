import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.diplom.dbo.CreatedUser;
import ru.yandex.diplom.generator.UserBodyGenerator;
import ru.yandex.diplom.restclient.UserClient;

import static org.hamcrest.Matchers.notNullValue;

public class CreateUserTest {
    UserClient userClient = new UserClient();
    UserBodyGenerator generator = new UserBodyGenerator();
    CreatedUser user = generator.generateRandomUser();


    // Create user test
    @Test
    public void createUserTest() { // создаем обычного пользователя
        Response response = userClient.createUserRequest(user);
        response.then().statusCode(200);
        userClient.clearTestData(user);
    }

    @Test
    public void cantCreateDuplicateUserTest() { // не можем создать дубликат
        userClient.createUserRequest(user);
        Response response = userClient.createUserRequest(user);
        response.then().statusCode(403);
        userClient.clearTestData(user);
    }

    @Test
    public void cantCreateEmptyUserTest() { // не можем создать пользователя с пустым телом
        CreatedUser user = new CreatedUser(null, null, null);
        Response response = userClient.createUserRequest(user);
        response.then().statusCode(403);
        //  userClient.clearTestData(user);
    }


    @Test
    public void loginUserTest() { // логин под существующим пользователем
        userClient.createUserRequest(user);
        Response response = userClient.loginUserRequest(user.getEmail(), user.getPassword());
        response.then().statusCode(200);
        response.then().assertThat().body(notNullValue());
        userClient.clearTestData(user);
    }

    @Test
    public void loginWrongCredentialUserTest() { // логин под существующим пользователем
        userClient.createUserRequest(user);
        Response response = userClient.loginUserRequest(user.getEmail(), user.getName());
        response.then().statusCode(401);
        userClient.clearTestData(user);
    }

}