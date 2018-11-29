package com.example.daiphongpc.retrofit.Retrofit;

import com.example.daiphongpc.retrofit.SinhVien;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    @Multipart
    @POST("uploadImage.php")
    Call<String> upLoadPhto(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("insert.php")
    Call<String> InsertData(@Field("taikhoan") String taikhoan,@Field("matkhau") String matKhau,@Field("hinhanh") String hinhanh);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<SinhVien>> loginData(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

}
