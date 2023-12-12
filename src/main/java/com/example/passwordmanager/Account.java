package com.example.passwordmanager;

public class Account {
    public String title;
    public String url;
    public String username;
    public String password;

    public Account(String title, String url, String username, String password) {
        this.title = title;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
