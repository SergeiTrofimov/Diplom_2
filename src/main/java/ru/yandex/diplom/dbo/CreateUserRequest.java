package ru.yandex.diplom.dbo;

public class CreateUserRequest {
    CreatedUser user;

    public CreateUserRequest(CreatedUser user) {
        this.user = user;
    }

    public CreatedUser getUser() {
        return user;
    }
}