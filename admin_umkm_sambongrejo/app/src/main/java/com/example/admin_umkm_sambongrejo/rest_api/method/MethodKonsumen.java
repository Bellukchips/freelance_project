package com.example.admin_umkm_sambongrejo.rest_api.method;

import com.example.admin_umkm_sambongrejo.rest_api.response.ResponseApi;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MethodKonsumen {
    // users
    @GET("data_konsumen/konsumen")
    Call<ResponseApi> getListKonsumen();

    @FormUrlEncoded
    @POST("data_konsumen/konsumen")
    Call<ResponseApi> saveKonsumen(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("address") String address

    );
    @FormUrlEncoded
    @POST("data_konsumen/konsumen/{id}")
    Call<ResponseApi> updateKonsumen(
            @Path("id") String id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("gender") String gender,
            @Field("address") String address
    );
    @DELETE("data_konsumen/konsumen/{id}")
    Call<ResponseApi> deleteKonsumen(
            @Path("id") String id
    );
}
