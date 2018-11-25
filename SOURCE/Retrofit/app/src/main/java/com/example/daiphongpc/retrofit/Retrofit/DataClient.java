package com.example.daiphongpc.retrofit.Retrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    @Multipart
    @POST("uploadImage.php")
    Call<String> upLoadPhto(@Part MultipartBody.Part photo);

}
