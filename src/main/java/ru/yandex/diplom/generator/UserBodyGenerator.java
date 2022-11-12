package ru.yandex.diplom.generator;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.diplom.dbo.CreatedUser;

public class UserBodyGenerator {
    @Step("Создаем имя случайного пользователя для регистрации")
    public CreatedUser generateRandomUser() { //Создаем тело случайного пользователя для регистрации
        CreatedUser user = new CreatedUser(
                RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(4) + ".com"
                , RandomStringUtils.randomAlphabetic(5)
                , RandomStringUtils.randomAlphabetic(8));
        return user;
    }
}