package dbo;

public class LoginUserRequest {
    // Переменные
    private String email;
    private String password;

    // Конструктор
    public LoginUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Сеттеры и геттеры
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
