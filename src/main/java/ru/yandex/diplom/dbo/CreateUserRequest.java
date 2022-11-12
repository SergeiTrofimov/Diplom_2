package ru.yandex.diplom.dbo;

public class CreateUserRequest {
    private String email;


    // Переменные
    private String password;
    private String name;

    // Конструктор
    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}