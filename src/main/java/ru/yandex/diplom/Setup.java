package ru.yandex.diplom;

public class Setup {
    private final static String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private final static String REGISTER_USER = "/api/auth/register";
    private final static String USER_LOGIN = "/api/auth/login";
    private final static String USER_LOGOUT = "/api/auth/logout";
    private final static String USER_RUD = "/api/auth/user";
    private final static String TOKEN_REFRESH = "/api/auth/token";
    private final static String CLIENT_ORDERS = "/api/orders";

    public String getBaseUri() {
        return BASE_URI;
    }
    public String getRegisterUser() {
        return REGISTER_USER;
    }
    public String getUserLogin() {return USER_LOGIN;}
    public String getUserLogout() {return USER_LOGOUT;}
    public String getUserRud() {return USER_RUD;}
    public String getTokenRefresh() {return TOKEN_REFRESH;}
    public String getClientOrders() {return CLIENT_ORDERS;}

}
