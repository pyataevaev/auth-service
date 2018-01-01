package com.osu.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Ekaterina Pyataeva
 */
public class UserCreateForm {

    @NotEmpty
    private String login = "";

    @NotEmpty
    private String password="";


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}