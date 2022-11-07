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

    //Getters и Setters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}