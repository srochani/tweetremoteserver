package com.company.tweetremote.model;

/**
 * Created by Sunil on 14/02/2017.
 */
public class UserMessageDTO {
    private String userLogin;
    private String message;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserMessageDTO{" +
                "userLogin='" + userLogin + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
