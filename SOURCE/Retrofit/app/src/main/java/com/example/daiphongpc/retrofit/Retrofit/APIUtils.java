package com.example.daiphongpc.retrofit.Retrofit;

public class APIUtils {
    public static final String BaseUrl="http://192.168.1.116/quanlysinhvien/";
    public static DataClient getData(){
        return RetrofitClient.getClinet(BaseUrl).create(DataClient.class);
    }
}
