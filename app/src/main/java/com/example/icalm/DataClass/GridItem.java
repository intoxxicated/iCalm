package com.example.icalm.DataClass;

public class GridItem {
    private int imageResource;
    private String text;

    public GridItem(int imageResource, String text) {
        this.imageResource = imageResource;
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return "GridItem{" +
                "imageResource=" + imageResource +
                ", text='" + text + '\'' +
                '}';
    }
}
