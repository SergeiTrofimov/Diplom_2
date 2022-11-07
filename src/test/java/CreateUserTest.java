import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.diplom.dbo.CreateUserResponse;
import ru.yandex.diplom.dbo.CreatedUser;
import ru.yandex.diplom.dbo.LoginUserResponse;
import ru.yandex.diplom.generator.UserBodyGenerator;
import ru.yandex.diplom.restclient.UserClient;

public class CreateUserTest {
    UserClient userClient = new UserClient();
    UserBodyGenerator generator = new UserBodyGenerator();
    CreatedUser user = generator.generateRandomUser();



    @Test
    public void createUserTest() {
        Response response = userClient.createUserRequest(user);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().as(CreateUserResponse.class).getMessage());
        //response.then().statusCode(200);
    }

    @Test
    public void loginUserTest() {
        Response response = userClient.loginUserRequest(user.getEmail(), user.getPassword());
        //response.then().statusCode(200);
        String token = response.getBody().as(LoginUserResponse.class).getAccessToken();
        System.out.println(token);
        System.out.println(response.getBody().as(LoginUserResponse.class).getAccessToken());
        Response deleteResponse = userClient.deleteUserRequest(token);
        System.out.println(deleteResponse.getStatusCode());
    }
}