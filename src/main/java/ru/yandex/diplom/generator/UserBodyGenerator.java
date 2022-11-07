package ru.yandex.diplom.generator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.diplom.dbo.CreatedUser;

public class UserBodyGenerator {
    public CreatedUser generateRandomUser() {
        CreatedUser user = new CreatedUser(
                RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(4) + ".com"
                , RandomStringUtils.randomAlphabetic(5)
                , RandomStringUtils.randomAlphabetic(8));
        return user;
    }
}
