package com.corndel.nozama.models;

import java.util.Objects;

public class Auth {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return Objects.equals(userName, auth.userName) && Objects.equals(password, auth.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
