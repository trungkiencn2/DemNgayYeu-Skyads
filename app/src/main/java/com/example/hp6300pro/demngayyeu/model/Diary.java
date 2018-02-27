package com.example.hp6300pro.demngayyeu.model;

import java.io.Serializable;

/**
 * Created by HP 6300 Pro on 1/4/2018.
 */

public class Diary implements Serializable{
    private String id;
    private String titleLove;
    private String uriBgCollapsing;
    private String date;
    private String contentLove;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleLove() {
        return titleLove;
    }

    public void setTitleLove(String titleLove) {
        this.titleLove = titleLove;
    }

    public String getUriBgCollapsing() {
        return uriBgCollapsing;
    }

    public void setUriBgCollapsing(String uriBgCollapsing) {
        this.uriBgCollapsing = uriBgCollapsing;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContentLove() {
        return contentLove;
    }

    public void setContentLove(String contentLove) {
        this.contentLove = contentLove;
    }

    public Diary(String id, String titleLove, String uriBgCollapsing, String date, String contentLove) {
        this.id = id;
        this.titleLove = titleLove;
        this.uriBgCollapsing = uriBgCollapsing;
        this.date = date;
        this.contentLove = contentLove;
    }

    public Diary() {
    }
}
