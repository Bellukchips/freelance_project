package com.example.admin_umkm_sambongrejo.rest_api.method;

import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MethodUsers {
    @FormUrlEncoded
    @POST("data_user/users")
    Call<ResponseApi> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

}
