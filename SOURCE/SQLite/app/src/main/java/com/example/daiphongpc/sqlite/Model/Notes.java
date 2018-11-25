package com.example.daiphongpc.sqlite.Model;

public class Notes {
    int id;
    String nhacNho;

    public Notes(int id, String nhacNho) {
        this.id = id;
        this.nhacNho = nhacNho;
    }


    public Notes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNhacNho() {
        return nhacNho;
    }

    public void setNhacNho(String nhacNho) {
        this.nhacNho = nhacNho;
    }
}
