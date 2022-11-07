package ru.yandex.diplom.dbo;

public class CreatedUser extends User {


    // Перменные
    private String password;

    // Конструктор
    public CreatedUser(String email, String name, String password) {
        super(email, name);
        this.password = password;
    }
    // Геттеры

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
