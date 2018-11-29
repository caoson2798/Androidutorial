package com.example.daiphongpc.btrcv_chat.Model;

public class Messenger {
    private String content;
    private int is_going;
    private String userName;

    public Messenger(String content, int is_going, String userName) {
        this.content = content;
        this.is_going = is_going;
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIs_going() {
        return is_going;
    }

    public void setIs_going(int is_going) {
        this.is_going = is_going;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
