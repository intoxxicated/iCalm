package com.example.icalm.DataClass;

public class Note {

    String key;
    String id;
    String content;

    public Note() {
    }

    public Note(String id, String content, String key) {
        this.id = id;
        this.content = content;
        this.key=key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
