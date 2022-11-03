package ru.yandex.diplom.dbo;

public class User {
    //Переменные
    private String email;
    private String name;

    //Конструктор
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    //Getters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}