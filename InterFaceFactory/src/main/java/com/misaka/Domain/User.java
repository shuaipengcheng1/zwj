package com.misaka.Domain;

import java.io.Serializable;

public class User implements Serializable {
    public int id;
    public String password;
    public String username;
    public String icon_src;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon_src() {
        return icon_src;
    }

    public void setIcon_src(String icon_src) {
        this.icon_src = icon_src;
    }

    public User() {
    }
}
