package com.example.passwordmanager;

public class Note {
    public int userId;
    public String text;
    public String title;

    public Note(int userId, String text, String title) {
        this.userId = userId;
        this.text = text;
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }
}
