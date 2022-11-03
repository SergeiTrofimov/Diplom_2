package ru.yandex.diplom.dbo;

public class LoginUserResponse {
    private boolean success;
    private String accessToken;
    private String refreshToken;
    private User user;

    //Конструктор

    public LoginUserResponse(boolean success, String accessToken, String refreshToken, User user) {
        this.success = success;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.user = user;
    }

    // Getters
    public boolean isSuccess() {
        return success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public User getUser() {
        return user;
    }
}
