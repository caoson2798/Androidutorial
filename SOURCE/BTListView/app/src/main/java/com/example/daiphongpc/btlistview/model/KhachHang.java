package com.example.daiphongpc.btlistview.model;

public class KhachHang {
    private String mName;
    private int mAge;
    private String mAdress;
    private boolean mSex;

    public KhachHang(String mName, int mAge, String mAdress, boolean mSex) {
        this.mName = mName;
        this.mAge = mAge;
        this.mAdress = mAdress;
        this.mSex = mSex;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }

    public String getmAdress() {
        return mAdress;
    }

    public void setmAdress(String mAdress) {
        this.mAdress = mAdress;
    }

    public boolean ismSex() {
        return mSex;
    }

    public void setmSex(boolean mSex) {
        this.mSex = mSex;
    }
}
