package com.example.icalm.DataClass;

public class Goal {
    private String title;
    private String description;

    public Goal(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}

