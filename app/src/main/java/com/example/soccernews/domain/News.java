package com.example.soccernews.domain;

public class News {

    private String Title;
    private String description;

    public News(String title, String description) {
        Title = title;
        this.description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
