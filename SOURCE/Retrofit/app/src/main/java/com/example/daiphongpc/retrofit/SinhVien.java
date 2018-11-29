package com.example.daiphongpc.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SinhVien {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("taikhoan")
    @Expose
    private String taikhoan;
    @SerializedName("matkhau")
    @Expose
    private String matkhau;
    @SerializedName("hinhanh")
    @Expose
    private String hinhanh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

}