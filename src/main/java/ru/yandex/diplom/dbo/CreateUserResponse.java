package ru.yandex.diplom.dbo;

public class CreateUserResponse {
    // Переменные
    private boolean success;
    private String message;

    // Конструктор
    public CreateUserResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    //Getters
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}