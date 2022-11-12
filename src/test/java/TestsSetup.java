import com.google.gson.Gson;
import ru.yandex.diplom.dbo.CreatedUser;
import ru.yandex.diplom.generator.OrderBodyGenerator;
import ru.yandex.diplom.generator.UserBodyGenerator;
import ru.yandex.diplom.restclient.OrderClient;
import ru.yandex.diplom.restclient.UserClient;

public class TestsSetup {
    protected static String token;
    protected static UserClient userClient = new UserClient();
    protected static OrderClient orderClient = new OrderClient();
    protected static UserBodyGenerator userGenerator = new UserBodyGenerator();
    protected static OrderBodyGenerator orderGenerator = new OrderBodyGenerator();
    protected static CreatedUser user = userGenerator.generateRandomUser();
    protected static Gson gson = new Gson();
}
