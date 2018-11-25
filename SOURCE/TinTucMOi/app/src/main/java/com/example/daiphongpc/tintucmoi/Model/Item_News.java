package com.example.daiphongpc.tintucmoi.Model;

import java.io.Serializable;

public class Item_News implements Serializable {
    private String title;
    private String link_news;

    public Item_News(String title, String link_news) {
        this.title = title;
        this.link_news = link_news;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink_news() {
        return link_news;
    }

    public void setLink_news(String link_news) {
        this.link_news = link_news;
    }
}
