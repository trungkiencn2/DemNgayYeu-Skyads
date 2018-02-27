package com.example.hp6300pro.demngayyeu.model;

/**
 * Created by HP 6300 Pro on 11/30/2017.
 */

public class Function {
    private Integer image;
    private String title;

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Function(Integer image, String title) {
        this.image = image;
        this.title = title;
    }

    public Function() {
    }
}
