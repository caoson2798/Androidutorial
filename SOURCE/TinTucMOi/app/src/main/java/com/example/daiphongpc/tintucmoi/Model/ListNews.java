package com.example.daiphongpc.tintucmoi.Model;

import java.io.Serializable;

public class ListNews implements Serializable {
    private String img;
    private String title;
    private String link;

    public ListNews(String img, String title, String link) {
        this.img = img;
        this.title = title;
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
